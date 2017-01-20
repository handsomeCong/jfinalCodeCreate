package platform.base.ext.taglib;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {

	private String data = "pageList";// 数据集合名字
	private String href; // 连接地址
	private StringBuffer output; // 页面输出

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public StringBuffer getOutput() {
		return output;
	}

	public void setOutput(StringBuffer output) {
		this.output = output;
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		 try {
             output = new StringBuffer();
             hander();
             this.pageContext.getOut().write(output.toString());
			 } catch (IOException e) {
			             e.printStackTrace();
		      }
	  return SKIP_BODY;
	}
	private void hander() {
		// 如果URL不包含? 则添加?
		if (href.indexOf("?") < 0) {
			href += "?";
		}
		if (href.endsWith("?") || href.endsWith("&amp;")) {
			href += "pageIndex=";
		} else {
			href += "&amp;pageIndex=";
		}
		HashMap pageList = (HashMap) this.pageContext.getRequest().getAttribute(data);
		if (pageList != null) {
			StringBuffer page = new StringBuffer();
			int pageNumber=Integer.valueOf(pageList.get("pageNumber")+"");
			int totalPage=Integer.valueOf(pageList.get("totalPage")+"");
			int totalRow=Integer.valueOf(pageList.get("totalRow")+"");
			int startPage = pageNumber-4;
			int endPage =  pageNumber+4;
			
			if(totalRow==0){
				pageNumber=0;
			}
			
			if(startPage<1){
				startPage=1;
			}
			
			if(endPage>totalPage){
				endPage=totalPage;
			}
			
			if(pageNumber<=8){
				startPage=1;
			}
			if(totalPage-pageNumber<8){
				endPage=totalPage;
			}
			
			page.append("<div class=\"sabrosus \">")
			    .append("&nbsp;&nbsp;&nbsp;");
			
			if(pageNumber<=1){
				page.append("<span>上一页</span>&nbsp;&nbsp;");
			}
			if(pageNumber>1){
				page.append("<a href=\""+href+(pageNumber-1)+" \">上一页</a>");
			}
			if(pageNumber>8){
				page.append("<a href=\""+href+"1 \">1</a>")
					.append("<a href=\""+href+"2 \">2</a>")
					.append("...");
			}
			
			for(int i=startPage;i<=endPage;i++){
				if(pageNumber==i){
					page.append("<span class=\"current\">"+i+"</span>");
				}else{
					page.append("<a href=\""+href+i+" \">"+i+"</a>");
				}
			}
			if(totalPage-pageNumber>=8){
				page.append("...")
					.append("<a href=\""+href+(totalPage-1)+" \">"+(totalPage-1)+"</a>")
					.append("<a href=\""+href+(totalPage)+" \">"+(totalPage)+"</a>");
			}
			
			if(pageNumber==totalPage){
				page.append("<span>下一页</span>");
			}else{
				page.append("<a href=\""+href+(pageNumber+1)+" \">下一页</a>");
				     
			}
			page.append("&nbsp;&nbsp; ");
			page.append("</div>");
			output.append(page);
		}
	}

}
