package payrestapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayCreateVo {
    private String payCode;
    private Long payType;
    private String cardRef;
    private String userCi;
    private Long payPrice;
}
