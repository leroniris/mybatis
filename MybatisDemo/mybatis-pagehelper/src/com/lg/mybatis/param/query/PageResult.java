package com.lg.mybatis.param.query;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResult {
    private List<?> result;//每一页的结果集
    private int totalCount;//结果总数

    private int currentPage = 1;//当前页
    private int pageSize = 3;//每页最多多少条数据
    private int prePage; //上一页
    private int nextPage; //下一页
    private int totalPage;//总页数

    public PageResult(List<?> result, int totalCount, int currentPage, int pageSize) {
        this.result = result;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.prePage = currentPage - 1 >= 1 ? currentPage - 1 : 1;
        this.nextPage = currentPage + 1 <=totalPage ? currentPage + 1 : totalPage;
        currentPage = currentPage > totalPage ? totalPage : currentPage;
    }
}
