<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.tag.mytag.com" prefix="page"  %>

<jsp:include page="/admin/include/head.jsp"></jsp:include>
<jsp:include page="/admin/include/left.jsp"></jsp:include>
<script src="${contextpath }/admin/js/common.js"></script>
<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>数据库配置管理</h1>
					</div>
					<jsp:include page="/admin/include/main_head.jsp" />					
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<a href="more-login.html">首页</a>
							<i class="icon-angle-right"></i>
						</li>
						<li>
							<a href="index.html">数据库配置管理</a>
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
									<button class="btn btn-small btn-waring" id="table_edit"><i class="icon-file"></i>&nbsp;&nbsp; 编 辑</button>
									&nbsp;&nbsp;
									<button class="btn btn-small btn-waring" id="table_delete"><i class="icon-file"></i>&nbsp;&nbsp; 删 除</button>
						 			 &nbsp;&nbsp;
						 			 
						 			 
						 			 
									<div class="input-append input-prepend">
												 
													<input type="text" placeholder="请输入用户名..." class='input-medium'>
									&nbsp;&nbsp;
													 
												  <input type="text" placeholder="请输入手机号码..." class='input-medium'>
							    &nbsp;&nbsp;
							    				 
												  <input type="text" placeholder="请输入姓名..." class='input-medium'>
									&nbsp;
									
								 
									</div>
										 
										 <button class="btn btn-success" type="button">查  询</button>
									
									
								</h4>
								
							</div>
							 
							<div class="box-content nopadding">
								<table class="table table-hover table-nomargin table-colored-header">
									<thead>
										<tr>
											<th><input type="checkbox" width="15px"  id="checkAll"/></th>
											<th>数据库名称</th>
											<th>数据库类型</th>
											<th>驱动类</th>
											<th>主机名</th>
											<th>端口号</th>
											<th>用户名</th>
										 
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${pageList.list }" var="item">
										 <tr>
										 <td><input type="checkbox" name="subBox" value="${item.ID }" /></td>
										 <td>${item.DB_NAME }</td>
										 <td>${item.NAME }</td>
										  <td>${item.DBDRIVER }</td>
										  <td>${item.HOST }</td>
										  <td>${item.POST }</td>
										  <td>${item.DBUSER_NAME }</td>
										 </tr>
									 </c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			<page:page href="${contextpath }/dbconfiginfo?username=${username }&pwd=${pwd }" data="pageList" />
			</div>
 
 <script language="javascript">
 
$(document).ready(function(){
	table_init("${contextpath}/dbconfiginfo","${contextpath}/dbconfiginfo");
});
</script>
<jsp:include page="/admin/include/footer.jsp"></jsp:include>