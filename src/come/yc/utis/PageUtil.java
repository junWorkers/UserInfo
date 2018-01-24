package come.yc.utis;

public class PageUtil {
	private int pageNo=1;
	private int pageSize=5;
	private int totalSize=0;
	private int totalPage=0;
	@Override
	public String toString() {
		return "PageUtil [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalSize=" + totalSize + ", totalPage=" + totalPage + "]";
	}
	
	public int getNextPageNo() {
		if(this.pageNo<this.totalPage){
			this.pageNo++;
		}else{
			this.pageNo=this.totalPage;
		}
		return this.pageNo;
	}
	
	public int getProPageNo() {
		if(this.pageNo>1){
			this.pageNo--;
		}else{
			this.pageNo=1;
		}
		return this.pageNo;
	}
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<=0){
			this.pageNo=1;
		}else if(this.totalPage!=0&& pageNo>this.totalPage){
			this.pageNo=this.totalPage;
		}else{
			this.pageNo = pageNo;
		}
		
	}
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		if(pageSize<=0){
			this.pageSize=5;
		}else{
			this.pageSize = pageSize;
		}
	}
	public int getTotalSize() {
		return totalSize;
	}
	
	public void setTotalSize(int totalSize) {
		if(totalSize<0){
			this.totalSize=0;
		}else{
			this.totalSize = totalSize;
		}
	}
	public int getTotalPage() {
		totalPage=this.totalSize%this.pageSize==0?this.totalSize/this.pageSize:this.totalSize/this.pageSize+1;
		return totalPage;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pageNo;
		result = prime * result + pageSize;
		result = prime * result + totalPage;
		result = prime * result + totalSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageUtil other = (PageUtil) obj;
		if (pageNo != other.pageNo)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (totalPage != other.totalPage)
			return false;
		if (totalSize != other.totalSize)
			return false;
		return true;
	}
	
	
}
