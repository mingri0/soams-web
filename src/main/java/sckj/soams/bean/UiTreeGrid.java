package sckj.soams.bean;

import java.util.List;
/**
 * easy ui TreeGrid 数据封装
 * @author 龙
 *
 */
public class UiTreeGrid {
	
	private int total;
	
	private List rows;
	
	
	
	public UiTreeGrid(List rows) {
		super();
		this.rows = rows;
		this.total = rows.size();
	}

	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<Object> getRows() {
		return rows;
	}
	
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
}
