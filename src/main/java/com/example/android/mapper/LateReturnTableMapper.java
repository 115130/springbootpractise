package com.example.android.mapper;

import com.example.android.domain.LateReturnTable;
import com.example.android.domain.LateReturnTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface LateReturnTableMapper {
    @SelectProvider(type=LateReturnTableSqlProvider.class, method="countByExample")
    long countByExample(LateReturnTableExample example);

    @DeleteProvider(type=LateReturnTableSqlProvider.class, method="deleteByExample")
    int deleteByExample(LateReturnTableExample example);

    @Delete({
        "delete from late_return_table",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into late_return_table (id, created_date, ",
        "hostel, last_modify, ",
        "student_id)",
        "values (#{id,jdbcType=BIGINT}, #{createdDate,jdbcType=TIMESTAMP}, ",
        "#{hostel,jdbcType=BIGINT}, #{lastModify,jdbcType=TIMESTAMP}, ",
        "#{studentId,jdbcType=BIGINT})"
    })
    int insert(LateReturnTable record);

    @InsertProvider(type=LateReturnTableSqlProvider.class, method="insertSelective")
    int insertSelective(LateReturnTable record);

    @SelectProvider(type=LateReturnTableSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="hostel", property="hostel", jdbcType=JdbcType.BIGINT),
        @Result(column="last_modify", property="lastModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT)
    })
    List<LateReturnTable> selectByExample(LateReturnTableExample example);

    @Select({
        "select",
        "id, created_date, hostel, last_modify, student_id",
        "from late_return_table",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="hostel", property="hostel", jdbcType=JdbcType.BIGINT),
        @Result(column="last_modify", property="lastModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT)
    })
    LateReturnTable selectByPrimaryKey(Long id);

    @UpdateProvider(type=LateReturnTableSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") LateReturnTable record, @Param("example") LateReturnTableExample example);

    @UpdateProvider(type=LateReturnTableSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") LateReturnTable record, @Param("example") LateReturnTableExample example);

    @UpdateProvider(type=LateReturnTableSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(LateReturnTable record);

    @Update({
        "update late_return_table",
        "set created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "hostel = #{hostel,jdbcType=BIGINT},",
          "last_modify = #{lastModify,jdbcType=TIMESTAMP},",
          "student_id = #{studentId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(LateReturnTable record);
}