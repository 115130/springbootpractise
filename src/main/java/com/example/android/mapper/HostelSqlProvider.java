package com.example.android.mapper;

import com.example.android.domain.Hostel;
import com.example.android.domain.HostelExample.Criteria;
import com.example.android.domain.HostelExample.Criterion;
import com.example.android.domain.HostelExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class HostelSqlProvider {
    public String countByExample(HostelExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("hostel");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(HostelExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("hostel");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Hostel record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("hostel");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getCount() != null) {
            sql.VALUES("count", "#{count,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedDate() != null) {
            sql.VALUES("created_date", "#{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getGrade() != null) {
            sql.VALUES("grade", "#{grade,jdbcType=DOUBLE}");
        }
        
        if (record.getLastModify() != null) {
            sql.VALUES("last_modify", "#{lastModify,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRemain() != null) {
            sql.VALUES("remain", "#{remain,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(HostelExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("count");
        sql.SELECT("created_date");
        sql.SELECT("grade");
        sql.SELECT("last_modify");
        sql.SELECT("remain");
        sql.FROM("hostel");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Hostel record = (Hostel) parameter.get("record");
        HostelExample example = (HostelExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("hostel");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getCount() != null) {
            sql.SET("count = #{record.count,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedDate() != null) {
            sql.SET("created_date = #{record.createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getGrade() != null) {
            sql.SET("grade = #{record.grade,jdbcType=DOUBLE}");
        }
        
        if (record.getLastModify() != null) {
            sql.SET("last_modify = #{record.lastModify,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRemain() != null) {
            sql.SET("remain = #{record.remain,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("hostel");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("count = #{record.count,jdbcType=INTEGER}");
        sql.SET("created_date = #{record.createdDate,jdbcType=TIMESTAMP}");
        sql.SET("grade = #{record.grade,jdbcType=DOUBLE}");
        sql.SET("last_modify = #{record.lastModify,jdbcType=TIMESTAMP}");
        sql.SET("remain = #{record.remain,jdbcType=INTEGER}");
        
        HostelExample example = (HostelExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Hostel record) {
        SQL sql = new SQL();
        sql.UPDATE("hostel");
        
        if (record.getCount() != null) {
            sql.SET("count = #{count,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedDate() != null) {
            sql.SET("created_date = #{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getGrade() != null) {
            sql.SET("grade = #{grade,jdbcType=DOUBLE}");
        }
        
        if (record.getLastModify() != null) {
            sql.SET("last_modify = #{lastModify,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRemain() != null) {
            sql.SET("remain = #{remain,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, HostelExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}