<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.tag.mytag.com" prefix="page"  %>

<jsp:include page="/admin/include/head.jsp"></jsp:include>
<jsp:include page="/admin/include/left.jsp"></jsp:include>
<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>表配置管理</h1>
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
							<a href="${contextpath }/dbconfiginfo">表配置管理</a>
							<i class="icon-angle-right"></i>
						</li>
						<li>
							<a href="#">查看</a>
						</li>
					</ul>
					<div class="close-bread">
						<a href="#"><i class="icon-remove"></i></a>
					</div>
					 
					
				</div>
  				<!--查看页面  -->
					 <div class="row-fluid">
					<div class="span12">
						 
							<div class="box box-color box-bordered">
								<div class="box-title">
									<h3><i class="icon-th-list"></i> 表配置查看</h3>
								</div>
							<div class="box-content nopadding">
								<form action="${contextpath }/dbconfiginfo/saveAdd" method="POST" class='form-horizontal form-bordered'>
								
									   <div class="control-group">
											<label for="textfield" class="control-label">数据库选择</label>
											<div class="controls">
												 ${configInfo.DB_NAME }
											</div>
										</div>
									 
											
									 <div class="control-group">
											<label for="textfield" class="control-label">表选择</label>
											<div class="controls">
											 ${info.ENGLISH_NAME }
											 </div>
									 </div>
									<div class="control-group">
										<label for="post" class="control-label">描述名称</label>
										<div class="controls">
										${info.SCENARIO_NAME }
 										</div>
									</div>
									<div class="control-group">
										<label for="db_name" class="control-label">包存放名称(**.**.**)</label>
										<div class="controls">
										${info.PACKAGE_PATCH }
 										</div>
									</div>
									<div class="control-group">
										<label for="dbuser_name" class="control-label">实体类名称</label>
										<div class="controls">
										${info.TABLE_OBJECT_NAME }
 										</div>
									</div>
									<br/>
									
								 
									
								</form>
								
							</div>
							<br/>
							<div class="box-content nopadding">
							<table class="table table-hover table-nomargin table-colored-header">
									  <tr>
										<thead>
										<th >字段名</th>
										<th>字段描述</th>
										<th style="width:200px">是否列表显示</th>
										<th style="width:200px">是否新增显示</th>
										<th style="width:200px">控件类型</th>
										<th style="width:200px">是否必填</th>
										<th style="width:200px">是否为搜索字段</th>
										</thead>
									  </tr>
									  <tbody id="columnTable">
								  <c:forEach items="${extendList }" var="item">
									  <tr>
									 <td>${item.FIELD_ENGLISH_NAME}</td>
									 <td>${item.FIELD_DESCRIBE}</td>
									 <td>
									    <c:choose>
									     	<c:when test="${item.IS_TALBE_DISPLAY=='true'}">是</c:when>
										 	<c:otherwise>否</c:otherwise>
									    </c:choose>
									 </td>
									<td>
										<c:choose>
											<c:when test="${item.IS_CREATE_DISPLAY=='true'}">是</c:when>
											<c:otherwise>否</c:otherwise>
										</c:choose>
									</td>
									<td>${item.CONTROL_TYPE}</td>
									<td>
										<c:choose>
											<c:when test="${item.IS_NEED=='true'}">是</c:when>
											<c:otherwise>否</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${item.IS_SEARCH=='true'}">是</c:when>
											<c:otherwise>否</c:otherwise>
										</c:choose>
									</td>  
									  </tr>
									  </c:forEach>
									  </tbody>
									</table>
							</div>
							
							<div class="form-actions">
										<a href='${contextpath}/configbasicinfo' class="btn btn-danger" >返 回</a>
							 </div>
						</div>
					</div>
				</div>
			  <!-- 新增页面  -->
					 				
			
				 
				 
				</div>
 			</div>
 
<script>
function getTableString(){
	var id=document.getElementById('db_type').value;
			$.ajax({
				 url:'${contextpath }/configbasicinfo/getTableItemString/'+id,
				 dataType:'json',
				 type:'post',
				 success:function(data){
					 $("#selectString").html(data.selectString);
					 }
		           });
		 
	 
}

function getColumnTableString(){
	var db_id=document.getElementById('db_type').value;
	var table_name=document.getElementById('table_select').value;
			$.ajax({
				 url:'${contextpath }/configbasicinfo/getColumnTableItemString?db_id='+db_id+'&table_name='+table_name,
				 dataType:'json',
				 type:'post',
				 success:function(data){
					 $("#columnTable").html(data.columnTableString);
					 }
		           });
		 
	 
}
</script>
<jsp:include page="/admin/include/footer.jsp"></jsp:include>