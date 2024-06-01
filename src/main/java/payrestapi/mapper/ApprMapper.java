package payrestapi.mapper;

import payrestapi.vo.ApprVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprMapper {
    void insertAppr(ApprVo apprVo);
}
