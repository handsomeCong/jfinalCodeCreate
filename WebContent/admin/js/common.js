function table_init(action_base_url,action_query_url){
	//action_base_url是action的url action_query_url是查询的URL
	  $("#checkAll").click(function() {
		 
		    $('input[name="subBox"]').prop("checked",this.checked);
		  });
 
	//删除按钮业务处理
	$("#table_delete").click(
		function()
		{
			var del_ids="" ;
			var count=0;
			var checkbox = $("input[name='subBox']");
			checkbox.each(function() {
				if (this.checked) {
					del_ids += this.value+",";
					count=count+1;
				}
			});
			if(count==0){
				
				alert('请选择要删除的数据！');
			}
			
			else{
				 
				var confirm_result = confirm("您确认删除您所选择的"+count+"条数据么？");
			if(confirm_result)
			{
				//ajax提交删除数据
				jQuery.ajax({
							type: "post", 
							url: action_base_url+"/delete", 
							dataType: "json",
							data:{action:'delete',ids:del_ids},
							success: function (data) { 
								alert(data.msg);
								window.location.href=action_query_url;
							} 
					});

				
			}
			}
		}
	);
	
	//导出源码
	$("#table_outsource").click(
		function()
		{
			var del_ids="" ;
			var count=0;
			var checkbox = $("input[name='subBox']");
			checkbox.each(function() {
				if (this.checked) {
					del_ids += this.value+",";
					count=count+1;
				}
			});
			if(count==0){
				
				alert('请选择要生成源码的表！');
			}
			
			else{
				 
				var confirm_result = confirm("您确认生成您所选择的"+count+"条数据么？");
			if(confirm_result)
			{
				window.location.href=action_base_url+"/outsource?ids="+del_ids;
			}
			}
		}
	);	
	
	//修改按钮业务处理
	$("#table_edit").click(
		function()
		{
			var a=0;
			var id="" ;
			var checkbox = $("input[name='subBox']");
			checkbox.each(function() {
				if (this.checked) {
					id = this.value;
					a++;
				}
			});
			if(id==""){
				alert("请选择要修改的记录!");
			}else{
			if(a!=1){
				alert("请选择一条要修改的记录!");
			}else{
				window.location.href=action_base_url+"/modify?id="+id;
				}
			}
		}
	);
	
	//新增按钮业务处理
	$("#table_add").click(
			function()
			{
				window.location.href=action_base_url+"/add";
			}
	);
}