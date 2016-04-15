package com.xxy.util.model;

/**
 * 分页bean
 * @author xingyuan
 * @date 2016-3-9
 * <!------------------>
 */
public class Page {
	
	/** 1.每页显示数量(everyPage) */
	private int everyPage;
	/** 2.总记录数(totalCount) */
	private int totalCount;
	/** 3.总页数(totalPage) */
	private int totalPage;
	/** 4.当前页(currentPage) */
	private int currentPage;
	/** 5.起始页(beginIndex) */
	private int beginIndex;
	/** 6.是否有上一页(hasPrePage) */
	private boolean hasPrePage;
	/** 7.是否有下一页(hasNextPage) */
	private boolean hasNextPage;
	
	/**
	 * @param everyPage 每页显示数量
	 * @param totalCount 总记录数
	 * @param totalPage 总页数
	 * @param currentPage 当前页
	 * @param beginIndex 起始页
	 * @param hasPrePage 是否有上一页
	 * @param hasNextPage 是否有下一页
	 */
	public Page(int everyPage, int totalCount, int totalPage, int currentPage,
			int beginIndex, boolean hasPrePage, boolean hasNextPage) {
		this.everyPage = everyPage;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.beginIndex = beginIndex;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}
	
	public Page() {}

	public int getEveryPage() {
		return everyPage;
	}
	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public boolean isHasPrePage() {
		return hasPrePage;
	}
	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
}
