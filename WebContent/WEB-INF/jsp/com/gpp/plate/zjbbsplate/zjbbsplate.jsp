<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.tag.mytag.com" prefix="page"  %>
<jsp:include page="/admin/include/head.jsp"></jsp:include>
<jsp:include page="/admin/include/left.jsp"></jsp:include>
<script src="${contextpath}/admin/js/common.js"></script>
<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>论坛分类</h1>
					</div>
					<jsp:include page="/admin/include/main_head.jsp" />					
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<a href="${contextpath}/">首页</a>
							<i class="icon-angle-right"></i>
						</li>
						<li>
							<a href="${contextpath}/zjbbsplate">论坛分类</a>
						</li>
					</ul>
					<div class="close-bread">
						<a href="#"><i class="icon-remove"></i></a>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="box box-color box-bordered">
						<h4>
							<div class="box">
								 <div class="btn-group" >
												<a href="#" data-toggle="dropdown" class="btn dropdown-toggle">导 出 <span class="caret"></span></a>
												<ul class="dropdown-menu">
													<li>
														<a href="#">导出excel</a>
													</li>
													<li>
														<a href="#">导出csv</a>
													</li>
													<li>
														<a href="#">导出pdf</a>
													</li>
												</ul>
									</div>
									&nbsp;&nbsp;
									<button class="btn btn-small btn-waring" id="table_add"><i class="icon-file"></i>&nbsp;&nbsp; 新 增</button>
									&nbsp;&nbsp;
									<button class="btn btn-small btn-waring" id="table_edit"><i class="icon-file"></i>&nbsp;&nbsp;编 辑</button>
									&nbsp;&nbsp;
									<button class="btn btn-small btn-waring" id="table_delete"><i class="icon-file"></i>&nbsp;&nbsp;删 除</button>
						 			 &nbsp;&nbsp;
						 			 
									<div class="input-append input-prepend">
																				
																			 									 										 											 											 										 									 										 											 											 										 									 										 									 										 									 										 									 									
																			<form method="post" action="${contextpath}/zjbbsplate">
																																																															  <input type="text" name="form_name" placeholder="请输入表单名称" value="${form_name}" class='input-medium'>&nbsp;&nbsp;
																																													  <input type="text" name="qybz" placeholder="请输入启用标志" value="${qybz}" class='input-medium'>&nbsp;&nbsp;
																																																																																														
																			<button class="btn btn-success" type="submit">查  询</button>
										</form>
																		</div>
								</h4>
								
							</div>
							 
							<div class="box-content nopadding">
								<table class="table table-hover table-nomargin table-colored-header">
									<thead>
										<tr>
											<th><input type="checkbox" width="15px"  id="checkAll"/></th>
																																																										   <th>表单名称</th>
																																															   <th>启用标志</th>
																																															   <th>排序号</th>
																																																																						   <th>创建时间</th>
																																		<th>操 作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageList.list}" var="item">
										 
									       <tr>
    											<td><input type="checkbox" name="subBox" value="${item.ID}" /></td>
    											    											 												    											     											<td>
    												    													${ item.form_name}
    												    											</td>	
				
    											 												    											     											<td>
    												    													${ item.qybz}
    												    											</td>	
				
    											 												    											     											<td>
    												    													${ item.orderList}
    												    											</td>	
				
    											 												    											 												    											     											<td>
    												    													<fmt:formatDate value="${ item.ctime}" var="ctime" pattern="yyyy-MM-dd"/>	
    													${ item.ctime}
    												    											</td>	
				
    											 																						  <td><a class="btn btn-small btn-waring" href="${contextpath}/zjbbsplate/view?id=${item.ID}">&nbsp; 查看</a></td>
										   </tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			<page:page href="${contextpath}/zjbbsplate?form_name=${form_name}&qybz=${qybz}" data="pageList" />
		</div>
 
<script language="javascript">
$(document).ready(function(){
	table_init("${contextpath}/zjbbsplate","${contextpath}/zjbbsplate?form_name=${form_name}&qybz=${qybz}");
});
</script>
<jsp:include page="/admin/include/footer.jsp"></jsp:include>