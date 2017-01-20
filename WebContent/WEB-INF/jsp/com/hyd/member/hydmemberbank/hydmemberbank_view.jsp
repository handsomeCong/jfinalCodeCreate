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
						<h1>会员银行管理新增</h1>
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
							<a href="${contextpath}/hydmemberbank">会员银行管理</a>
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
									<h3><i class="icon-th-list"></i>${objCN}查看</h3>
								</div>
							<div class="box-content nopadding">
								<form action="${contextpath}/hydmemberbank/saveModify" method="POST" class='form-horizontal  form-bordered form-validate' >
																			<input type="hidden"  name="${aLObjName}.ID" value="${ model.ID}" id="ID"   />
									                                      										<input type="hidden"  name="${aLObjName}.ID" value="${ model.ID}" id="ID"   />
																			                                        <div class="control-group">
                                          <label for="cardholder" class="control-label">持卡人：</label>
                                          <div class="controls">
																							 ${ model.cardholder}
																					  </div>
                                        </div>
																			                                         										<input type="hidden"  name="${aLObjName}.ID" value="${ model.ID}" id="ID"   />
																			                                        <div class="control-group">
                                          <label for="bank_name" class="control-label">银行类型：</label>
                                          <div class="controls">
																							 ${ model.bank_name}
																					  </div>
                                        </div>
																			                                         										<input type="hidden"  name="${aLObjName}.ID" value="${ model.ID}" id="ID"   />
																			                                        <div class="control-group">
                                          <label for="bank_no" class="control-label">银行卡号：</label>
                                          <div class="controls">
																							 ${ model.bank_no}
																					  </div>
                                        </div>
																			                                         										<input type="hidden"  name="${aLObjName}.ID" value="${ model.ID}" id="ID"   />
																			                                        <div class="control-group">
                                          <label for="bank_sub_branch" class="control-label">开户行支行名称：</label>
                                          <div class="controls">
																							 ${ model.bank_sub_branch}
																					  </div>
                                        </div>
																			                                         										<input type="hidden"  name="${aLObjName}.ID" value="${ model.ID}" id="ID"   />
																												                                         										<input type="hidden"  name="${aLObjName}.ID" value="${ model.ID}" id="ID"   />
																			                                        <div class="control-group">
                                          <label for="is_default" class="control-label">是否默认：</label>
                                          <div class="controls">
																							 ${ model.is_default}
																					  </div>
                                        </div>
																			                                         									<div class="form-actions">
										<a href='${contextpath}/hydmemberbank' class="btn btn-danger" >返 回</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			  <!-- 查看页面  -->
					 				
			
				 
				 
				</div>
 			</div>
 
 
<jsp:include page="/admin/include/footer.jsp"></jsp:include>