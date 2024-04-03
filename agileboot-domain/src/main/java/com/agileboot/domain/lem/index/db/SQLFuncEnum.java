package com.agileboot.domain.lem.index.db;

import com.github.yulichang.wrapper.enums.BaseFuncEnum;

public enum SQLFuncEnum implements BaseFuncEnum {
    SUM("SUM(%s)"),
    COUNT("COUNT(%s)"),
    MAX("MAX(%s)"),
    MIN("MIN(%s)"),
    AVG("AVG(%s)"),
    LEN("LEN(%s)"),
    DATE("DATE(%s)"),
    ;
    
    private final String sql;
    
    private SQLFuncEnum(String sql) {
        this.sql = sql;
    }
    
    @Override
    public String getSql() {
        return this.sql;
    }
}
