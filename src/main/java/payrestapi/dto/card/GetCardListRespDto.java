package payrestapi.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import payrestapi.consts.ResCode;
import payrestapi.vo.CardVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCardListRespDto {
    private List<CardVo> cardList;
    private int resultcode = ResCode.SUCCESS.value();
    private String resultmessage;
}
