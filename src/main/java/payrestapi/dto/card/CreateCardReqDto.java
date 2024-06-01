package payrestapi.dto.card;

import lombok.Data;

@Data
public class CreateCardReqDto {
    private String cardno;
    private String expired;
    private String crc;
    private String ref_id;

}
