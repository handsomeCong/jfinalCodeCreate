<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.tag.mytag.com" prefix="page"  %>

<jsp:include page="/admin/include/head.jsp"></jsp:include>
<jsp:include page="/admin/include/left.jsp"></jsp:include>
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
							<a href="${contextpath }/dbconfiginfo">数据库配置管理</a>
							<i class="icon-angle-right"></i>
						</li>
						<li>
							<a href="#">编辑</a>
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
									<h3><i class="icon-th-list"></i>数据库配置编辑新增</h3>
								</div>
							<div class="box-content nopadding">
								<form action="${contextpath }/dbconfiginfo/saveModify" method="POST" class='form-horizontal form-bordered form-validate' id="bb">
								<input type="hidden" name="dbConfigInfo.id" value="${model.id }">
									<div class="control-group">
										<label for="name" class="control-label">数据库类型</label>
										<div class="controls">
										${model.name }
 										</div>
									</div>
									<div class="control-group">
										<label for="dbdriver" class="control-label">驱 动</label>
										<div class="controls">
										${model.dbdriver }
 										</div>
									</div>
									<div class="control-group">
										<label for="host" class="control-label">主机IP或名称</label>
										<div class="controls">
											<input type="text" name="dbConfigInfo.host" value="${model.host }" id="host" class="input-xlarge" data-rule-required="true">
										</div>
									</div>
									<div class="control-group">
										<label for="post" class="control-label">端口</label>
										<div class="controls">
											<input type="text" name="dbConfigInfo.post" value="${model.post }" id="confirmfield" class="input-xlarge" data-rule-required="true">
										</div>
									</div>
									<div class="control-group">
										<label for="db_name" class="control-label">数据库名称</label>
										<div class="controls">
											<input type="text" name="dbConfigInfo.db_name" value="${model.db_name }" id="db_name" class="input-xlarge" data-rule-required="true">
										</div>
									</div>
									<div class="control-group">
										<label for="dbuser_name" class="control-label">用户名</label>
										<div class="controls">
											<input type="text" name="dbConfigInfo.dbuser_name" id="dbuser_name" value="${model.dbuser_name }" class="input-xlarge"  data-rule-required="true">
										</div>
									</div>
									 
									<div class="control-group">
										<label for="password" class="control-label">密码</label>
										<div class="controls">
											<input type="password" name="dbConfigInfo.password" id="password" value="${model.password }" class="input-xlarge"   data-rule-required="true">
										</div>
									</div>
									<div class="form-actions">
										<input type="submit" class="btn btn-primary" value="保 存">
										<a href='${contextpath}/dbconfiginfo' class="btn btn-danger" >返 回</a>
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