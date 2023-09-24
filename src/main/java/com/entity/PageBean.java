package com.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageBean {

    private Integer page; // 第几页
    private Integer pageSize; // 每页记录数
    private Integer start;  // 起始页


    public PageBean(Integer page, Integer pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (page-1)*pageSize;
    }


}
