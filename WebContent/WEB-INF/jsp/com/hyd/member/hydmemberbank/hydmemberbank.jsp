<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.tag.mytag.com" prefix="page"%>
<jsp:include page="/admin/include/head.jsp"></jsp:include>
<jsp:include page="/admin/include/left.jsp"></jsp:include>
<script src="${contextpath}/admin/js/common.js"></script>
<div id="main">
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>会员银行管理</h1>
			</div>
			<jsp:include page="/admin/include/main_head.jsp" />
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><a href="${contextpath}/">首页</a> <i
					class="icon-angle-right"></i></li>
				<li><a href="${contextpath}/hydmemberbank">会员银行管理</a></li>
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
							<div class="btn-group">
								<a href="#" data-toggle="dropdown" class="btn dropdown-toggle">导
									出 <span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">导出excel</a></li>
									<li><a href="#">导出csv</a></li>
									<li><a href="#">导出pdf</a></li>
								</ul>
							</div>
							&nbsp;&nbsp;
							<button class="btn btn-small btn-waring" id="table_add">
								<i class="icon-file"></i>&nbsp;&nbsp; 新 增
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-small btn-waring" id="table_edit">
								<i class="icon-file"></i>&nbsp;&nbsp;编 辑
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-small btn-waring" id="table_delete">
								<i class="icon-file"></i>&nbsp;&nbsp;删 除
							</button>
							&nbsp;&nbsp;

							<div class="input-append input-prepend">


								<form method="post" action="${contextpath}/hydmemberbank">
									<input type="text" name="cardholder" placeholder="请输入持卡人"
										value="${cardholder}" class='input-medium'>&nbsp;&nbsp;
									<input type="text" name="bank_no" placeholder="请输入银行卡号"
										value="${bank_no}" class='input-medium'>&nbsp;&nbsp; <input
										type="text" name="bank_sub_branch" placeholder="请输入开户行支行名称"
										value="${bank_sub_branch}" class='input-medium'>&nbsp;&nbsp;

									<button class="btn btn-success" type="submit">查 询</button>
								</form>
							</div>
					</h4>

				</div>

				<div class="box-content nopadding">
					<table
						class="table table-hover table-nomargin table-colored-header">
						<thead>
							<tr>
								<th><input type="checkbox" width="15px" id="checkAll" /></th>
								<th>持卡人</th>
								<th>银行类型</th>
								<th>银行卡号</th>
								<th>开户行支行名称</th>
								<th>是否默认</th>
								<th>操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageList.list}" var="item">

								<tr>
									<td><input type="checkbox" name="subBox"
										value="${item.ID}" /></td>
									<td>${ item.cardholder}</td>

									<td>${ item.bank_name}</td>

									<td>${ item.bank_no}</td>

									<td>${ item.bank_sub_branch}</td>

									<td>${ item.is_default}</td>

									<td><a class="btn btn-small btn-waring"
										href="${contextpath}/hydmemberbank/view/${item.ID}">&nbsp;
											查看</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<page:page
		href="${contextpath}/hydmemberbank/cardholder=${cardholder}&bank_no=${bank_no}&bank_sub_branch=${bank_sub_branch}"
		data="pageList" />
</div>

<script language="javascript">
	$(document)
			.ready(
					function() {
						table_init(
								"${contextpath}/hydmemberbank",
								"${contextpath}/hydmemberbank/cardholder=${cardholder}&bank_no=${bank_no}&bank_sub_branch=${bank_sub_branch}");
					});
</script>
<jsp:include page="/admin/include/footer.jsp"></jsp:include>