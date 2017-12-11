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

    List<SheetData> selectByExampleWithBLOBs(SheetDataExample example);

    List<SheetData> selectByExample(SheetDataExample example);

    SheetData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SheetData record, @Param("example") SheetDataExample example);

    int updateByExampleWithBLOBs(@Param("record") SheetData record, @Param("example") SheetDataExample example);

    int updateByExample(@Param("record") SheetData record, @Param("example") SheetDataExample example);

    int updateByPrimaryKeySelective(SheetData record);

    int updateByPrimaryKeyWithBLOBs(SheetData record);

    int updateByPrimaryKey(SheetData record);

    List<SheetData> selectBySheetName(String sheetName);

    List<String> findSheetNameByUserId(String userId);

    SheetData findByUseridAndSheetName(@Param("userId") String userId,@Param("sheetName") String sheetName);

}