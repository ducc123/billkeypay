package payrestapi.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import payrestapi.consts.ResCode;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCardRespDto {
    private int Resultcode = ResCode.SUCCESS.value();
    private String Resultmessage;
}
