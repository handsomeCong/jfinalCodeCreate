<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.tag.mytag.com" prefix="page"  %>

<jsp:include page="/admin/include/head.jsp"></jsp:include>
<jsp:include page="/admin/include/left.jsp"></jsp:include>
<script type="text/javascript">
 function outEclipse(){
	 var type=$("#archeType").val();
	 var projectName=$("#projectName").val();
	 var jdkVersion=$("#jdkVersion").val();
	 window.location.href="${contextpath}/configbasicinfo/outEclipseSource?type="+type+"&projectName="+projectName+"&jdkVersion="+jdkVersion;
 }
</script>

<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>eclipse配置生成</h1>
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
							<a href="${contextpath }/dbconfiginfo">eclipse配置生成</a>
							<i class="icon-angle-right"></i>
						</li>
						<li>
							<a href="#">查询</a>
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
									<h3><i class="icon-th-list"></i>eclipse配置生成</h3>
								</div>
							<div class="box-content nopadding">
								<form action="${contextpath }/dbconfiginfo/saveAdd" method="POST" class='form-horizontal form-bordered form-validate' id="bb">
								
									 <div class="control-group">
										<label for="db_name" class="control-label">项目名称</label>
										<div class="controls">
											<input type="text" name="projectName" id="projectName" class="input-xlarge" data-rule-required="true">
										</div>
									</div>
									  <div class="control-group">
											<label for="textfield" class="control-label">模板选择</label>
											<div class="controls">
												<select name="archeType" id="archeType" class='input-xlarge'>
												<option value="">===请选择生成的项目类型===</option>
												<option value="service">service</option>
												<option value="web">web</option>
												</select>
											</div>
										</div>
									 <div class="control-group">
											<label for="textfield" class="control-label">java版本</label>
											<div class="controls">
												<select name="jdkVersion" id="jdkVersion" class='input-xlarge'>
												<option value="1.6">1.6</option>
												<option value="1.7">1.7</option>
												<option value="1.8">1.8</option>
												</select>
											</div>
										</div>
									 
									 <div class="control-group" id="msg">
										 
									</div>
									 
									<div class="form-actions">
										<input type="button " class="btn btn-primary" value="生成" onclick="outEclipse();">
 									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			  <!-- 新增页面  -->
				 
				</div>
 			</div>
 
 
<jsp:include page="/admin/include/footer.jsp"></jsp:include>