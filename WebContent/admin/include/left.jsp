<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div id="left">
		<form action="#" method="POST" class='search-form'>
			<div class="search-pane">
				<input type="text" name="search" placeholder="点击搜索...">
				<button type="submit">
					<i class="icon-search"></i>
				</button>
			</div>
		</form>
		<div class="subnav">
			<div class="subnav-title">
				<a href="#" class='toggle-subnav'><i class="icon-angle-down"></i><span>代码中心</span></a>
			</div>
			<ul class="subnav-menu">

				<li><a href="${contextpath }/dbconfiginfo">数据库配置管理</a></li>
				<li><a href="${contextpath }/configbasicinfo">表配置管理</a></li>
				<li><a href="${contextpath }/toUpdateSql">sql执行</a></li>
				<li><a href="${contextpath }/toGenerateEclipse">eclipse配置生成</a></li>
				<li class='dropdown'><a href="#" data-toggle="dropdown">产品管理</a>
					<ul class="dropdown-menu">
						<li><a href="#">产品列表</a></li>
						<li><a href="plugins-dragdrop.html">门户首页</a></li>
						<li class='dropdown-submenu'><a href="#"
							data-toggle="dropdown" class='dropdown-toggle'>产品详细</a>
							<ul class="dropdown-menu">
								<li><a href="${contextpath }/hydmemberbank">产品1</a></li>
								<li><a href="#">产品2</a></li>
								<li><a href="#">产品3</a></li>
							</ul></li>
					</ul></li>
			</ul>
		</div>



	</div>
 