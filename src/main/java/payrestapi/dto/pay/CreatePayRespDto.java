package payrestapi.dto.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import payrestapi.consts.ResCode;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePayRespDto {
    private int resultcode = ResCode.SUCCESS.value();
    private String resultmessage;
}
