package payrestapi.dto.pay;

import lombok.Data;

@Data
public class CreatePayReqDto {
    private Long pay_price;
    private Long pay_type;
    private String card_ref;
    private String pay_code;
    private String user_ci;

}
