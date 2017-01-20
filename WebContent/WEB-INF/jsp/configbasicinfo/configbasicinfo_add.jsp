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
							<a href="#">新增</a>
						</li>
					</ul>
					<div class="close-bread">
						<a href="#"><i class="icon-remove"></i></a>
					</div>
					 
					
				</div>
  				<!--新增页面  -->
					 <div class="row-fluid">
					<div class="span12">
						<div class="box">
							<div class="box box-color box-bordered">
								<div class="box-title">
									<h3><i class="icon-th-list"></i> 表配置新增</h3>
								</div>
							<div class="box-content nopadding">
								<form action="${contextpath }/configbasicinfo/saveAdd" method="POST" class='form-horizontal form-validate form-bordered' id="bb">
								
									   <div class="control-group">
											<label for="textfield" class="control-label">数据库选择</label>
											<div class="controls">
												<select name="configBasicInfo.db_type" id="db_type" class=' input-xlarge' onchange="getTableString();">
												<option value="">===请选择数据库===</option>
												<c:forEach items="${infoList }" var="info">
												<option value="${info.id }">${info.db_name }</option>
												</c:forEach>
												</select>
											</div>
										</div>
									 
											
									 <div class="control-group">
											<label for="textfield" class="control-label">表选择</label>
											<div class="controls" id="selectString">
												<select  class='select2-me input-xlarge' >
												<option value="">===请选择数据库===</option>
												</select>
											</div>
									 </div>
									<div class="control-group">
										<label for="post" class="control-label">描述名称</label>
										<div class="controls">
											<input type="text" name="configBasicInfo.SCENARIO_NAME" id="SCENARIO_NAME" class="input-xlarge" data-rule-required="true">
										</div>
									</div>
									<div class="control-group">
										<label for="db_name" class="control-label">包存放名称(**.**.**)</label>
										<div class="controls">
											<input type="text" name="configBasicInfo.PACKAGE_PATCH" id="PACKAGE_PATCH" class="input-xlarge" data-rule-required="true">
										</div>
									</div>
								 
									<table class="table table-hover table-nomargin table-colored-header">
									  <tr>
										<thead>
										<th >字段名</th>
										<th>字段描述</th>
										<th style="width:200px">是否列表显示</th>
										<th style="width:200px">是否新增显示</th>
										<th style="width:200px">控件类型</th>
										<th style="width:200px">是否必填</th>
										<th style="width:200px">是否为搜索条件</th>
										<th style="width:200px">数据库字段类型</th>
										</thead>
									  </tr>
									  <tbody id="columnTable">
									  </tbody>
									</table>
								 
									<div class="form-actions">
										<input type="submit" class="btn btn-primary" value="保 存">
										<a href='${contextpath}/configbasicinfo' class="btn btn-danger" >返 回</a>
									</div>
								</form>
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