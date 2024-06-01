package payrestapi.service;

import payrestapi.exception.NoSuchDataException;
import payrestapi.mapper.ApprMapper;
import payrestapi.vo.ApprVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprService {
    @Autowired
    private ApprMapper apprMapper;

    public void createAppr(ApprVo apprVo) {
        apprMapper.insertAppr(apprVo);
    }

}
