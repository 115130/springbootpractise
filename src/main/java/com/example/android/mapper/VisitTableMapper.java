package com.example.android.mapper;

import com.example.android.domain.VisitTable;
import com.example.android.domain.VisitTableExample;
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

public interface VisitTableMapper {
    @SelectProvider(type=VisitTableSqlProvider.class, method="countByExample")
    long countByExample(VisitTableExample example);

    @DeleteProvider(type=VisitTableSqlProvider.class, method="deleteByExample")
    int deleteByExample(VisitTableExample example);

    @Delete({
        "delete from visit_table",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into visit_table (id, accessed_hostel, ",
        "accessed_student, created_date, ",
        "last_modify, phone)",
        "values (#{id,jdbcType=BIGINT}, #{accessedHostel,jdbcType=BIGINT}, ",
        "#{accessedStudent,jdbcType=BIGINT}, #{createdDate,jdbcType=TIMESTAMP}, ",
        "#{lastModify,jdbcType=TIMESTAMP}, #{phone,jdbcType=VARCHAR})"
    })
    int insert(VisitTable record);

    @InsertProvider(type=VisitTableSqlProvider.class, method="insertSelective")
    int insertSelective(VisitTable record);

    @SelectProvider(type=VisitTableSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="accessed_hostel", property="accessedHostel", jdbcType=JdbcType.BIGINT),
        @Result(column="accessed_student", property="accessedStudent", jdbcType=JdbcType.BIGINT),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modify", property="lastModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR)
    })
    List<VisitTable> selectByExample(VisitTableExample example);

    @Select({
        "select",
        "id, accessed_hostel, accessed_student, created_date, last_modify, phone",
        "from visit_table",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="accessed_hostel", property="accessedHostel", jdbcType=JdbcType.BIGINT),
        @Result(column="accessed_student", property="accessedStudent", jdbcType=JdbcType.BIGINT),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modify", property="lastModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR)
    })
    VisitTable selectByPrimaryKey(Long id);

    @UpdateProvider(type=VisitTableSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") VisitTable record, @Param("example") VisitTableExample example);

    @UpdateProvider(type=VisitTableSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") VisitTable record, @Param("example") VisitTableExample example);

    @UpdateProvider(type=VisitTableSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisitTable record);

    @Update({
        "update visit_table",
        "set accessed_hostel = #{accessedHostel,jdbcType=BIGINT},",
          "accessed_student = #{accessedStudent,jdbcType=BIGINT},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "last_modify = #{lastModify,jdbcType=TIMESTAMP},",
          "phone = #{phone,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(VisitTable record);
}