package cn.medemede.excelcard.dao;

import cn.medemede.excelcard.model.SheetData;
import cn.medemede.excelcard.model.SheetDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SheetDataMapper {
    long countByExample(SheetDataExample example);

    int deleteByExample(SheetDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SheetData record);

    int insertSelective(SheetData record);

    List<SheetData> selectByExample(SheetDataExample example);

    SheetData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SheetData record, @Param("example") SheetDataExample example);

    int updateByExample(@Param("record") SheetData record, @Param("example") SheetDataExample example);

    int updateByPrimaryKeySelective(SheetData record);

    int updateByPrimaryKey(SheetData record);
}