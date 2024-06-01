package payrestapi.dto.appr;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import payrestapi.consts.ResCode;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateApprRespDto {
    private int Resultcode = ResCode.SUCCESS.value();
    private String Resultmessage;
}
