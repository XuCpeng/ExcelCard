package cn.medemede.excelcard.dao;

import cn.medemede.excelcard.model.SheetColumn;
import cn.medemede.excelcard.model.SheetColumnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SheetColumnMapper {
    long countByExample(SheetColumnExample example);

    int deleteByExample(SheetColumnExample example);

    int deleteByPrimaryKey(String sheetName);

    int insert(SheetColumn record);

    int insertSelective(SheetColumn record);

    List<SheetColumn> selectByExampleWithBLOBs(SheetColumnExample example);

    List<SheetColumn> selectByExample(SheetColumnExample example);

    SheetColumn selectByPrimaryKey(String sheetName);

    int updateByExampleSelective(@Param("record") SheetColumn record, @Param("example") SheetColumnExample example);

    int updateByExampleWithBLOBs(@Param("record") SheetColumn record, @Param("example") SheetColumnExample example);

    int updateByExample(@Param("record") SheetColumn record, @Param("example") SheetColumnExample example);

    int updateByPrimaryKeySelective(SheetColumn record);

    int updateByPrimaryKeyWithBLOBs(SheetColumn record);

    int updateByPrimaryKey(SheetColumn record);

    List<SheetColumn> selectByLocked();

    List<SheetColumn> selectAll();


}