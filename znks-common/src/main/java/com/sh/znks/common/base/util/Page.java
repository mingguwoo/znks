package com.sh.znks.common.base.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class Page<T> {

	private List<T> content = new ArrayList<T>();
	private int page;
	private int pageSize = 20;
	private long totalRows;
	private int totalPage;

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	private boolean hasNext;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		if (totalRows > 0 && pageSize > 0) {
			this.totalPage = (int) Math.ceil((double) totalRows / (double) pageSize);
		}
		this.totalRows = totalRows;
	}

	public void setTotalRows(long totalRows) {
		if (totalRows > 0 && pageSize > 0) {
			this.totalPage = (int) Math.ceil((double) totalRows / (double) pageSize);
		}
		this.totalRows = totalRows;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
