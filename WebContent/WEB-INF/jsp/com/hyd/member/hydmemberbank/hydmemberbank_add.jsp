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
						<div class="box box-color box-bordered">
								<div class="box-title">
									<h3><i class="icon-th-list"></i>${objCN}新增</h3>
								</div>
							<div class="box-content nopadding">
								<form action="${contextpath}/hydmemberbank/saveAdd" method="POST" class='form-horizontal form-bordered form-validate' >
									 
																			                                       										                                        <div class="control-group">
                                          <label for="cardholder" class="control-label">持卡人：</label>
                                          <div class="controls">
																						   <input type="text" class="input-medium" id="cardholder" name="hydMemberBank.cardholder" placeholder="请输入持卡人" data-rule-required="true"/>
 																					  </div>
                                        </div>
										                                       										                                        <div class="control-group">
                                          <label for="bank_name" class="control-label">银行类型：</label>
                                          <div class="controls">
																						   <input type="text" class="input-medium" id="bank_name" name="hydMemberBank.bank_name" placeholder="请输入银行类型" data-rule-required="true"/>
 																					  </div>
                                        </div>
										                                       										                                        <div class="control-group">
                                          <label for="bank_no" class="control-label">银行卡号：</label>
                                          <div class="controls">
																						   <input type="text" class="input-medium" id="bank_no" name="hydMemberBank.bank_no" placeholder="请输入银行卡号" data-rule-required="true"/>
 																					  </div>
                                        </div>
										                                       										                                        <div class="control-group">
                                          <label for="bank_sub_branch" class="control-label">开户行支行名称：</label>
                                          <div class="controls">
																						   <input type="text" class="input-medium" id="bank_sub_branch" name="hydMemberBank.bank_sub_branch" placeholder="请输入开户行支行名称" data-rule-required="true"/>
 																					  </div>
                                        </div>
										                                       										                                       										                                        <div class="control-group">
                                          <label for="is_default" class="control-label">是否默认：</label>
                                          <div class="controls">
																							<select id="is_default"  name="hydMemberBank.is_default"   class='select2-me input-xlarge'><option value="true">是</option><option value="false">否</option></select>
																					  </div>
                                        </div>
										                                       									<div class="form-actions">
										<input type="submit" class="btn btn-primary" value="保 存">
										<a href='${contextpath}/hydmemberbank' class="btn btn-danger" >返 回</a>
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