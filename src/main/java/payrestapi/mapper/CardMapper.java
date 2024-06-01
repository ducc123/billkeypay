package payrestapi.mapper;

import payrestapi.vo.CardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardMapper {
    CardVo getCardByRef(String ref_id);
    void insertCard(CardVo cardVo);
}
