<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>代码生成工具</title>
<jsp:include page="/admin/include/include.jsp"></jsp:include>
</head>
<body>
<div id="navigation">
<div class="container-fluid">
			<a href="#" id="brand">code</a>
			<a href="#" class="toggle-nav"><i class="icon-reorder"></i></a>
			<ul class='main-nav'>
				<li class='active'>
					<a href="${contextpath }/">
						<span>首 页</span>
					</a>
				</li>
				<li>
					<a href="#" data-toggle="dropdown" class='dropdown-toggle'>
						<span>测试标签</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="forms-basic.html">测试1</a>
						</li>
						<li>
							<a href="forms-extended.html">测试2</a>
						</li>
						 
					</ul>
				</li>
			 
			</ul>
			<div class="user">
				<ul class="icon-nav">
					<li class='dropdown'>
						<a href="#" class='dropdown-toggle' data-toggle="dropdown"><i class="icon-envelope-alt"></i><span class="label label-lightred">4</span></a>
						<ul class="dropdown-menu pull-right message-ul">
							<li>
								<a href="#">
									<img src="img/demo/user-1.jpg"  alt="">
									<div class="details">
										<div class="name">Jane Doe</div>
										<div class="message">
											找你有事
										</div>
									</div>
								</a>
							</li>
							 
							<li>
								<a href="components-messages.html"  class='more-messages'>消息中心 <i class="icon-arrow-right"></i></a>
							</li>
						</ul>
					</li>
					 
					<li class='dropdown colo'>
						<a href="#" class='dropdown-toggle' data-toggle="dropdown"><i class="icon-tint"></i></a>
						<ul class="dropdown-menu pull-right theme-colors">
							<li class="subtitle">
								主题选择
							</li>
							<li>
								<span class='red'></span>
								<span class='orange'></span>
								<span class='green'></span>
								<span class="brown"></span>
								<span class="blue"></span>
								<span class='lime'></span>
								<span class="teal"></span>
								<span class="purple"></span>
								<span class="pink"></span>
								<span class="magenta"></span>
								<span class="grey"></span>
								<span class="darkblue"></span>
								<span class="lightred"></span>
								<span class="lightgrey"></span>
								<span class="satblue"></span>
								<span class="satgreen"></span>
							</li>
						</ul>
					</li>
					<li>
						<a href="more-locked.html"  class='lock-screen' rel='tooltip' title="Lock screen" data-placement="bottom"><i class="icon-lock"></i></a>
					</li>
				</ul>
				<div class="dropdown">
					<a href="#" class='dropdown-toggle' data-toggle="dropdown">管理员<img src="img/demo/user-avatar.jpg"  alt=""></a>
					<ul class="dropdown-menu pull-right">
						<li>
							<a href="more-userprofile.html" tppabs="">个人信息</a>
						</li>
						<li>
							<a href="#">设置</a>
						</li>
						<li>
							<a href="#" >退 出</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
 </div>