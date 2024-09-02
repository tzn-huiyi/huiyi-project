package com.huiyi.huiyiproject.entity.base;

import lombok.Data;

@Data
public class PageParam {

    /**
     * 当前页数
     */
    private int currentPage;
    /**
     * 页面大小
     */
    private int pageSize;

}
