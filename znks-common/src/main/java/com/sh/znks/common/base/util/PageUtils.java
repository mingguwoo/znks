package com.sh.znks.common.base.util;

import java.util.List;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class PageUtils {
    /**
     * 使用全量数据设置分页结果（适用于请求接口等复杂场景）
     * @param data          ： 全量数据list
     * @param currentPage  ： 当前页面数
     * @param pageSize     ： 每个页面的记录数
     * @return  分页页面
     */
    public static <T> Page<T> setPageWithAllData(List<T> data, Integer currentPage, Integer pageSize)
    {
        Page<T> page = new Page<T>();
        page.setPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRows(data.size());

        // 分页的第一条数据的偏移量(下标) inclusive
        int fromIndex = (currentPage - 1) * pageSize;
        // 分页的最后一条数据的偏移量(下标) exclusive
        int toIndex = currentPage * pageSize;

        // 最后一页
        if (toIndex  >= page.getTotalRows()) {
            page.setHasNext(false);
            // 重新修正下标
            toIndex = data.size();
        } else {
            page.setHasNext(true);
        }

        if (currentPage > page.getTotalPage()) {
            page.setContent(null);
        } else {
            page.setContent(data.subList(fromIndex, toIndex));
        }

        return page;
    }

    /**
     * 直接设置分页结果（适用于已经处理好分页的结构，如在数据库中分好页的查询）
     * @param data          ： 当前分页的数据list
     * @param totalRows    ： 总的记录数
     * @param currentPage  ： 当前页面数
     * @param pageSize     ： 每个页面的记录数
     * @return  分页页面
     */
    public static <T> Page<T> setPageWithDirectData(List<T> data, int totalRows, Integer currentPage, Integer pageSize)
    {
        Page<T> page = new Page<T>();
        page.setPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRows(totalRows);

        // 是最后一页
        if (currentPage * pageSize  >= page.getTotalRows()) {
            page.setHasNext(false);
            // 重新修正下标
        } else {
            page.setHasNext(true);
        }

        page.setContent(data);

        return page;
    }


    /**
     * 设置一个空的分页（适用于查询数据不存在时候的默认返回）
     * @param currentPage  ： 当前页面数
     * @param pageSize     ： 每个页面的记录数
     * @return  分页页面
     */
    public static <T> Page<T> setEmptyPage(Integer currentPage, Integer pageSize)
    {
        Page<T> page = new Page<T>();
        page.setPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRows(0);
        page.setHasNext(false);
        page.setContent(null);

        return page;
    }
}
