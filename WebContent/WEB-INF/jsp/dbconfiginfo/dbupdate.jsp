<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.tag.mytag.com" prefix="page"  %>

<jsp:include page="/admin/include/head.jsp"></jsp:include>
<jsp:include page="/admin/include/left.jsp"></jsp:include>
<script type="text/javascript">
function executeSql(){
	 var sql=$("#sql").val();
	 var db_type=$("#db_type").val();
	 $.ajax({
	        cache: true,
	        type: "POST",
	        url:"${contextpath}/executeSql?db_type="+db_type,
	        data:$('#bb').serialize(),
 	        async: false,
	        error: function(request) {
	            alert("表单提交失败");
	        },
	        success: function(data) {
	            if(data.FLAG='T'){
	             $("#msg").html(data.MSG);
	            }else{
	            	alert(data.MSG);
	            }
	        }
	    });
}
</script>

<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>数据库查询分析器</h1>
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
							<a href="${contextpath }/dbconfiginfo">数据库查询分析器</a>
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
									<h3><i class="icon-th-list"></i>数据库查询分析器</h3>
								</div>
							<div class="box-content nopadding">
								<form action="${contextpath }/dbconfiginfo/saveAdd" method="POST" class='form-horizontal form-bordered form-validate' id="bb">
								
									 
									  <div class="control-group">
											<label for="textfield" class="control-label">数据库选择</label>
											<div class="controls">
												<select name="db_type" id="db_type" class='input-xlarge'>
												<option value="">===请选择数据库===</option>
												<c:forEach items="${infoList }" var="info">
												<option value="${info.id }">${info.db_name }</option>
												</c:forEach>
												</select>
											</div>
										</div>
									 
									<div class="control-group">
										<label for="sql" class="control-label">执行sql语句</label>
										<div class="controls">
										<textarea name="sql" id="sql" class="input-block-level" rows="5" >
										
										</textarea>

										</div>
									</div>
									 <div class="control-group" id="msg">
										 
									</div>
									 
									<div class="form-actions">
										<input type="button " class="btn btn-primary" value="执行" onclick="executeSql();">
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