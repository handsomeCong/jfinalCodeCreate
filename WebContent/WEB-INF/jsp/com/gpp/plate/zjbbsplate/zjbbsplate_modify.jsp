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
						<h1>论坛分类编辑</h1>
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
  				<!--编辑页面  -->
					 <div class="row-fluid">
					<div class="span12">
						<div class="box box-color box-bordered">
								<div class="box-title">
									<h3><i class="icon-th-list"></i>${objCN}编辑</h3>
								</div>
							<div class="box-content nopadding">
								<form action="${contextpath}/zjbbsplate/saveModify" method="POST" class='form-horizontal  form-bordered form-validate' >
									 
																												<input type="hidden"  name="zjBbsPlate.ID" value="${ model.ID}" id="ID"   />
									                                      																			                                        <div class="control-group">
                                          <label for="form_name" class="control-label">表单名称：</label>
                                          <div class="controls">
											
																							<select id="form_name"  name="zjBbsPlate.form_name"   class='select2-me input-xlarge'><option value="true">是</option><option value="false">否</option></select>
																						 
										  </div>
                                        </div>
																			                                         																			                                        <div class="control-group">
                                          <label for="qybz" class="control-label">启用标志：</label>
                                          <div class="controls">
											
																							<input type="radio" id="qybz"  name="zjBbsPlate.qybz" />
																						 
										  </div>
                                        </div>
																			                                         																			                                        <div class="control-group">
                                          <label for="orderList" class="control-label">排序号：</label>
                                          <div class="controls">
											
																							<input type="checkbox" id="orderList"  name="zjBbsPlate.orderList" />
																						 
										  </div>
                                        </div>
																			                                         																												                                         																			                                        <div class="control-group">
                                          <label for="ctime" class="control-label">创建时间：</label>
                                          <div class="controls">
											
											                                            <input type="text" class="input-medium datepick" id="ctime" name="zjBbsPlate.ctime" value="${ model.ctime}" data-rule-required="true"/>
																						 
										  </div>
                                        </div>
																			                                         									<div class="form-actions">
										<input type="submit" class="btn btn-primary" value="保 存">
										<a href='${contextpath}/zjbbsplate' class="btn btn-danger" >返 回</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			  <!-- 编辑页面  -->
					 				
			
				 
				 
				</div>
 			</div>
 
 
<jsp:include page="/admin/include/footer.jsp"></jsp:include>