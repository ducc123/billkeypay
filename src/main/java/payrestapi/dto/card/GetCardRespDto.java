package payrestapi.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import payrestapi.consts.ResCode;
import payrestapi.vo.CardVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCardRespDto {
    private CardVo card;
    private int resultcode = ResCode.SUCCESS.value();
    private String resultmessage;
}
