package payrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import payrestapi.consts.ResCode;
import payrestapi.dto.pay.CreatePayReqDto;
import payrestapi.dto.pay.CreatePayRespDto;
import payrestapi.dto.pay.DetailPayRespDto;
import payrestapi.dto.pay.GetPayListRespDto;
import payrestapi.exception.NoSuchDataException;
import payrestapi.service.PayService;
import payrestapi.service.UserService;
import payrestapi.vo.PayCreateVo;

@Slf4j
@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @Autowired
    private UserService userService;

    @GetMapping("/pay/list")
    public GetPayListRespDto getPayList() {
        GetPayListRespDto getPayListRespDto = new GetPayListRespDto();
        try {
            getPayListRespDto.setPayList(payService.getPayList());
        } catch (NoSuchDataException e) {
            getPayListRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            getPayListRespDto.setResultmessage("No pay exists.");
        } catch (Exception e) {
            log.error("[PayController getPayList]", e);
            getPayListRespDto.setResultcode(ResCode.UNKNOWN.value());
            getPayListRespDto.setResultmessage(e.getLocalizedMessage());
        }
        return getPayListRespDto;
    }

    @GetMapping("/pay/detail/{pay_code}")
    public DetailPayRespDto getPayDetail(@PathVariable String pay_code) {
        DetailPayRespDto detailPayRespDto = new DetailPayRespDto();
        //log.info("pay_code {}",pay_code);
        try {
            detailPayRespDto.setPay_idx(payService.getPayDetail(pay_code));
        } catch (NoSuchDataException e) {
            detailPayRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            detailPayRespDto.setResultmessage("No pay exists.");
        } catch (Exception e) {
            log.error("[PayController getPayDetail]", e);
            detailPayRespDto.setResultcode(ResCode.UNKNOWN.value());
            detailPayRespDto.setResultmessage("정상적인 결제정보가 아닙니다.");
        }
        return detailPayRespDto;
    }

    @PostMapping("/pay_insert")
    public CreatePayRespDto createPay(@RequestBody CreatePayReqDto createPayReqDto) {

        CreatePayRespDto createPayRespDto = new CreatePayRespDto();

        try {
            //기등록된 ref_id인지, 혹은 정상적인 ref_id인지 체크
            userService.getUserByUserCi(createPayReqDto.getUser_ci());
        } catch (NoSuchDataException e) {
            createPayRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            createPayRespDto.setResultmessage("No such card exists.");
        }

        //최소금액 가능 검증
        if (createPayReqDto.getPay_price()<=100) {
            createPayRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            createPayRespDto.setResultmessage("0원 이상을 입력해주세요.");
            return createPayRespDto;
        }

        try {
            PayCreateVo payCreateVo = new PayCreateVo(createPayReqDto.getPay_code(),createPayReqDto.getPay_type(),createPayReqDto.getCard_ref(),createPayReqDto.getUser_ci(),createPayReqDto.getPay_price());
            log.info("[appr] "+payCreateVo);
            payService.createPay(payCreateVo);
            createPayRespDto.setResultmessage("정상적으로 결제가 완료되었습니다.");
        } catch (DataIntegrityViolationException e) {
            createPayRespDto.setResultcode(ResCode.NULL_VALUE.value());
            createPayRespDto.setResultmessage("필수 값이 빠졌습니다.");
        } catch (Exception e) {
            log.error("[CardController createCard]", e);
            createPayRespDto.setResultcode(ResCode.UNKNOWN.value());
            createPayRespDto.setResultmessage(e.getLocalizedMessage());
        }
        return createPayRespDto;
    }

}