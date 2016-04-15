package com.xxy.util.model;
/**
 * 分页信息辅助类
 * @author xingyuan
 * @date 2016-3-9
 * <!------------------>
 */
public class PageUtil {
	
	/**
	 * 创建分页对象
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param everyPage 每页显示数量
	 * @param totalCount 总条数
	 * @param currentPage 当前页
	 * @return Page
	 */
	public static Page createPage(int everyPage,int totalCount,int currentPage) {
		everyPage = getEveryPage(everyPage);
		currentPage = getCurrentPage(currentPage);
		int totalPage = getTotalPage(everyPage, totalCount);
		int beginIndex = getBeginIndex(everyPage, currentPage);
		boolean hasPrePage = getHasPrePage(currentPage);
		boolean hasNextPage = getHasNextPage(totalPage, currentPage);
		return new Page(everyPage, totalCount, totalPage, currentPage,
				beginIndex, hasPrePage,  hasNextPage);
	}
	
	/**
	 * 创建分页对象
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param page 分页bean
	 * @param totalCount 总记录数
	 * @return Page
	 */
	public static Page createPage(Page page,int totalCount) {
		int everyPage = getEveryPage(page.getEveryPage());
		int currentPage = getCurrentPage(page.getCurrentPage());
		int totalPage = getTotalPage(everyPage, totalCount);
		int beginIndex = getBeginIndex(everyPage, currentPage);
		boolean hasPrePage = getHasPrePage(currentPage);
		boolean hasNextPage = getHasNextPage(totalPage, currentPage);
		return new Page(everyPage, totalCount, totalPage, currentPage,
				beginIndex, hasPrePage,  hasNextPage);
	}
	
	/**
	 * 设置每页显示记录数
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param everyPage 每页显示条数
	 * @return int
	 */
	public static int getEveryPage(int everyPage) {
		return everyPage == 0 ? 10 : everyPage;
	}
	
	/**
	 * 设置当前页
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param currentPage 当前页,默认第一页
	 * @return int
	 */
	public static int getCurrentPage(int currentPage) {
		return currentPage == 0 ? 1 : currentPage;
	}
	
	/**
	 * 设置总页数
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param everyPage 每页显示数量
	 * @param totalCount 总记录数
	 * @return int
	 */
	public static int getTotalPage(int everyPage,int totalCount) {
		int totalPage = 0;
		if(totalCount % everyPage == 0) {
			totalPage = totalCount / everyPage;
		} else {
			totalPage = totalCount / everyPage + 1;
		}
		return totalPage;
	}
	
	/**
	 * 设置起始点
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param everyPage 每页显示数量
	 * @param currentPage 当前页
	 * @return int
	 */
	public static int getBeginIndex(int everyPage,int currentPage) {
		return (currentPage - 1) * everyPage;
	}
	
	/**
	 * 设置是否有上一页
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param currentPage 当前页
	 * @return boolean
	 */
	public static boolean getHasPrePage(int currentPage) {
		return currentPage == 1 ? false : true;
	}
	
	/**
	 * 设置是否有下一个
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param totalPage 总页数
	 * @param currentPage 当前页
	 * @return boolean
	 */
	public static boolean getHasNextPage(int totalPage, int currentPage) {
		return currentPage == totalPage || totalPage == 0 ? false : true;
	}
	
}
