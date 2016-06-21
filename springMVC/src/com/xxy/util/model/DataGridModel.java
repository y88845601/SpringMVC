package com.xxy.util.model;

import java.util.ArrayList;
import java.util.List;

public class DataGridModel {
	
	/** 总条数 */
	private Long total = 0L;
	@SuppressWarnings("rawtypes")
	private List rows = new ArrayList();
	
	@SuppressWarnings({ "rawtypes" })
	public DataGridModel(Long total,List rows){
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings({ "rawtypes" })
	public void setRows(List rows) {
		this.rows = rows;
	}
	

}
