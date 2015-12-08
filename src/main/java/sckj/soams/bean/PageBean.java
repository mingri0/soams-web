package sckj.soams.bean;

import java.io.Serializable;

/**
 * 翻页设置.
 * @author LYM
 */
public class PageBean implements Serializable {
	/** Comment for <code>serialVersionUID</code> . */
	private static final long serialVersionUID = -8132783218617126174L;

	/**
	 * 创建新的页.
	 * @param recordCount
	 * @param pageSize
	 * @return
	 */
	public static PageBean createPageBean(long recordCount, int pageSize) {
		PageBean bean = new PageBean();
		bean.setPage(recordCount, pageSize);

		return bean;
	}

	public PageBean(int startRecord, int endRecord) {
		start = startRecord;
		end = endRecord;
	}
	
    // 分页查询开始记录位置  
    private int start;  
    // 分页查看结束位置  
    private int end;   
	
	private int pageCount;

	private int pageSize = 20;

	private int lastPageSize;

	private long recordCount;

	private int currentPage = 1;
	
	

	/**
	 * Creates a new PageBean object.
	 */
	public PageBean() {
		super();
	}

	/**
	 * Creates a new PageBean object.
	 * @param pageSize
	 */
	public PageBean(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 设计需要多少页.
	 */
	public void calculate() {
		pageCount = (int) recordCount / pageSize;

		lastPageSize = (int) recordCount % pageSize;

		if (lastPageSize == 0) {
			lastPageSize = pageSize;
		} else {
			pageCount++;
		}
	}

	/**
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 得到当前页的第一条记录的记录数.
	 * @returncurrentPage
	 */
	public int getCurrentPageFirstRecord() {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * 当前的页数.
	 * @return
	 */
	public int getCurrentPageSize() {
		if (pageCount == currentPage) {
			return getLastPageSize();
		} else {
			return pageSize;
		}
	}

	/**
	 * 设置当前页.
	 * @return
	 */
	public int[] getCurrentScope() {
		if (pageCount == 0) {
			return new int[] { 0, 0 };
		}

		int[] result = new int[2];

		if (currentPage > pageCount) {
			currentPage = pageCount;
		}

		result[0] = (currentPage - 1) * pageSize;
		result[1] = result[0] + pageSize;

		if (currentPage == pageCount) {
			result[1] = result[0] + lastPageSize;
		}

		return result;
	}

	/**
	 * 获取最后记录
	 * @return
	 */
	public int getEndRecord() {
		if (pageCount == currentPage) {
			int lastRecord = getLastPageSize();
			if (lastRecord > getStartRecord()) {
				return lastRecord;
			}
			return lastRecord + getCurrentPageFirstRecord();
		} else {
			return getCurrentPageFirstRecord() + pageSize;
		}

	}

	/**
	 * 得到记录数.
	 * @return PageBean.recordCount
	 */
	public int getIntegerRecordCount() {
		return (int) recordCount;
	}

	/**
	 * 最后一页的大小.
	 */
	public int getLastPageSize() {
		return lastPageSize;
	}

	/**
	 * 下一页.
	 * @return
	 */
	public int getNextPageNo() {
		int result = currentPage + 1;

		if (result > pageCount) {
			result = pageCount;
		}

		return result;
	}

	/**
	 * 总的页数.
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 上一页.
	 * @return
	 */
	public int getPrivPageNo() {
		int result = currentPage - 1;

		if (result < 1) {
			result = 1;
		}

		return result;
	}

	/**
	 * 得到总共有多少条记录.
	 * @return
	 */
	public long getRecordCount() {
		return (pageCount - 1) * pageSize + lastPageSize;
	}

	/**
	 * 获取开始记录
	 * @return
	 */
	public int getStartRecord() {
		return getCurrentPageFirstRecord() + 1;
	}

	/**
	 * 是否是第一页.
	 * @return
	 */
	public boolean isFirst() {
		return currentPage == 1;
	}

	/**
	 * 是否是最后一页.
	 * @return
	 */
	public boolean isLast() {
		return pageCount <= currentPage;
	}

	/**
	 * 是否还有下一页.
	 * @return
	 */
	public boolean isNext() {
		return pageCount > currentPage;
	}

	/**
	 * 是否还有上一页.
	 * @return
	 */
	public boolean isPriv() {
		return currentPage > 1;
	}

	/**
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @param lastPageSize
	 */
	public void setLastPageSize(int lastPageSize) {
		this.lastPageSize = lastPageSize;
	}

	/**
	 * 设置页.
	 * @param recordCount
	 * @param pageSize
	 */
	public void setPage(long recordCount, int pageSize) {
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		calculate();
	}

	/**
	 * @param pageCount
	 */
	protected void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @param recordCount
	 */
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;

		if (pageSize != 0) {
			calculate();
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
