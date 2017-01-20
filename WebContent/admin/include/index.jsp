<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<jsp:include page="/admin/include/head.jsp"></jsp:include>
<jsp:include page="/admin/include/left.jsp"></jsp:include>
<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>通用页面</h1>
					</div>
<jsp:include page="/admin/include/main_head.jsp"></jsp:include>					
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<a href="more-login.html">首页</a>
							<i class="icon-angle-right"></i>
						</li>
						<li>
							<a href="index.html">代码生成</a>
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
									<button class="btn btn-small btn-waring"  onClick="add();"><i class="icon-file"></i>&nbsp;&nbsp; 新 增</button>
									&nbsp;&nbsp;
									<button class="btn btn-small btn-waring" ><i class="icon-file"></i>&nbsp;&nbsp; 编 辑</button>
									&nbsp;&nbsp;
									<button class="btn btn-small btn-waring" ><i class="icon-file"></i>&nbsp;&nbsp; 删 除</button>
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
											<th><input type="checkbox" width="15px"/></th>
											<th>数据库名称</th>
											 
											<th>数据库类型</th>
											<th>主机名</th>
											<th>用户名</th>
										 
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="checkbox"/></td>
											<td>Trident</td>
											 
											<td class='hidden-350'>Win 95+</td>
											<td class='hidden-1024'>4</td>
											<td class='hidden-480'>X</td>
										</tr>
										<tr>
											<td><input type="checkbox"/></td>
											<td>Presto</td>
											 
											<td class='hidden-350'>N800</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>A</td>
										</tr>
										<tr>
											<td><input type="checkbox"/></td>
											<td>Misc</td>
										 
											<td class='hidden-350'>Embedded devices</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>A</td>
										</tr>
										<tr>
											<td><input type="checkbox"/></td>
											<td>Misc</td>
											 
											<td class='hidden-350'>Embedded devices</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>X</td>
										</tr>
										<tr>
												<td><input type="checkbox"/></td>
											<td>Misc</td>
										 
											<td class='hidden-350'>Text only</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>X</td>
										</tr>
										<tr>
												<td><input type="checkbox"/></td>
											<td>Misc</td>
											 
											<td class='hidden-350'>Text only</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>X</td>
										</tr>
										<tr>
												<td><input type="checkbox"/></td>
											<td>Misc</td>
											 
											<td class='hidden-350'>Text only</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>X</td>
										</tr>
										<tr>
												<td><input type="checkbox"/></td>
											<td>Misc</td>
											 
											<td class='hidden-350'>Text only</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>X</td>
										</tr>
										<tr>
												<td><input type="checkbox"/></td>
											<td>Misc</td>
											 
											<td class='hidden-350'>Text only</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>X</td>
										</tr>
										<tr>
												<td><input type="checkbox"/></td>
											<td>Misc</td>
											 
											<td class='hidden-350'>Text only</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>X</td>
										</tr>
										<tr>
												<td><input type="checkbox"/></td>
											<td>Misc</td>
											 
											<td class='hidden-350'>Text only</td>
											<td class='hidden-1024'>-</td>
											<td class='hidden-480'>X</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			 
				 
		
		
				 
			</div>
			
		</div>	
<jsp:include page="/admin/include/footer.jsp"></jsp:include>