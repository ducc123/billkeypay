package payrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import payrestapi.consts.ResCode;
import payrestapi.dto.card.*;
import payrestapi.exception.NoSuchDataException;
import payrestapi.service.CardService;
import payrestapi.service.UserService;
import payrestapi.vo.CardVo;
import payrestapi.vo.UserVo;

@Slf4j
@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @GetMapping("/card/{ref_id}")
    public GetCardRespDto getCardinfo(@PathVariable String ref_id) {

        GetCardRespDto getCardRespDto = new GetCardRespDto();

        try {
            //기등록된 ref_id인지, 혹은 정상적인 ref_id인지 체크
            userService.getUserByUserCi(ref_id);
        } catch (NoSuchDataException e) {
            getCardRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            getCardRespDto.setResultmessage("No such card exists.");
        }

        try {
            getCardRespDto.setCard(cardService.getCardByRef(ref_id));
        } catch (NoSuchDataException e) {
            getCardRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            getCardRespDto.setResultmessage("No such card exists.");
        } catch (Exception e) {
            log.error("[CardController getCardByRef_id]", e);
            getCardRespDto.setResultcode(ResCode.UNKNOWN.value());
            getCardRespDto.setResultmessage(e.getLocalizedMessage());
        }
        return getCardRespDto;
    }

    @PostMapping("/card_insert")
    public CreateCardRespDto createCard(@RequestBody CreateCardReqDto createCardReqDto) {
        CreateCardRespDto createCardRespDto = new CreateCardRespDto();
        try {
            CardVo cardVo = new CardVo(createCardReqDto.getCardno(), createCardReqDto.getExpired(), createCardReqDto.getCrc(), "card_ref1");
            cardService.createCard(cardVo);
            createCardRespDto.setResultmessage("정상적으로 등록이 완료되었습니다.");
        } catch (DataIntegrityViolationException e) {
            createCardRespDto.setResultcode(ResCode.NULL_VALUE.value());
            createCardRespDto.setResultmessage("'cardnum', 'expired', 'crc' are required.");
        } catch (Exception e) {
            log.error("[CardController createCard]", e);
            createCardRespDto.setResultcode(ResCode.UNKNOWN.value());
            createCardRespDto.setResultmessage(e.getLocalizedMessage());
        }
        return createCardRespDto;
    }

}
