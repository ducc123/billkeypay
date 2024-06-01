package payrestapi.service;

import payrestapi.exception.NoSuchDataException;
import payrestapi.mapper.CardMapper;
import payrestapi.vo.CardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardMapper cardMapper;

    public CardVo getCardByRef(String ref_id) {
        CardVo cardVo = cardMapper.getCardByRef(ref_id);
        if (cardVo == null)
            throw new NoSuchDataException("No such card exists.");
        return cardVo;
    }

    public void createCard(CardVo cardVo) {
        cardMapper.insertCard(cardVo);
    }

}
