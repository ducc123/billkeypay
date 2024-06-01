package payrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import payrestapi.consts.ResCode;
import payrestapi.dto.appr.CreateApprReqDto;
import payrestapi.dto.appr.CreateApprRespDto;
import payrestapi.dto.pay.CreatePayRespDto;
import payrestapi.exception.NoSuchDataException;
import payrestapi.service.ApprService;
import payrestapi.service.UserService;
import payrestapi.vo.ApprVo;
import payrestapi.vo.CardVo;

@Slf4j
@RestController
public class ApprController {
    private final ApprService apprService;

    @Autowired
    private UserService userService;

    public ApprController(ApprService apprService) {
        this.apprService = apprService;
    }

    @PostMapping("/appr_insert")
    public CreateApprRespDto createApprove(@RequestBody CreateApprReqDto createApprReqDto) {
        CreateApprRespDto createApprRespDto = new CreateApprRespDto();

        try {
            //기등록된 ref_id인지, 혹은 정상적인 ref_id인지 체크
            userService.getUserByUserCi(createApprReqDto.getUser_ci());
        } catch (NoSuchDataException e) {
            createApprRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            createApprRespDto.setResultmessage("No such card exists.");
        }

        try {
            //기등록된 pay_code인지, 혹은 정상적인 pay_code인지 체크
            userService.getUserByUserCi(createApprReqDto.getUser_ci());
        } catch (NoSuchDataException e) {
            createApprRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            createApprRespDto.setResultmessage("No such card exists.");
        }

        try {
            ApprVo apprVo = new ApprVo(createApprReqDto.getAppr_price(), createApprReqDto.getAppr_status(),createApprReqDto.getPay_code(), createApprReqDto.getUser_ci());
            log.info("[appr] "+apprVo);
            apprService.createAppr(apprVo);
            createApprRespDto.setResultmessage("정상적으로 승인이 완료되었습니다.");
        } catch (DataIntegrityViolationException e) {
            createApprRespDto.setResultcode(ResCode.NULL_VALUE.value());
            createApprRespDto.setResultmessage("필수 값이 빠졌습니다.");
        } catch (Exception e) {
            log.error("[CardController createCard]", e);
            createApprRespDto.setResultcode(ResCode.UNKNOWN.value());
            createApprRespDto.setResultmessage(e.getLocalizedMessage());
        }
        return createApprRespDto;
    }
}
