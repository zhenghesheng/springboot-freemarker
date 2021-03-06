﻿<!DOCTYPE html>
<html>
<head>
	<#import "spring.ftl" as spring/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CRM系统</title>
<link href="/style/authority/login_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/scripts/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#login_sub").click(function(){
			$("#submitForm").attr("action", "/test/login").submit();
		});
	});
	
	/*回车事件*/
	function EnterPress(e){ //传入 event 
		var e = e || window.event; 
		if(e.keyCode == 13){ 
			$("#submitForm").attr("action", "/test/login").submit();
		} 
	} 
</script>
</head>
<body>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<form id="submitForm" action="" method="post">
						<div id="login_tip">
							<span id="login_err" class="sty_txt2"></span>
						</div>
						<div>
							<@spring.message code="username"/>
							 <input type="text" name="username" class="username" id="name">
						</div>
						<div>
							<@spring.message code="password"/>
							<input type="password" name="password" class="pwd" id="pwd" onkeypress="EnterPress(event)" onkeydown="EnterPress()">
						</div>
						<div id="btn_area">
							<input type="button" class="login_btn" id="login_sub" value="<@spring.message code='login'/>">
							<input type="reset" class="login_btn" id="login_ret" value="<@spring.message code='reset'/>">
						</div>
						<a href="/test/tologin?l=zh_CN">中文</a>
						<a href="/test/tologin?l=en_US">英文</a>
						<a href="/test/tologin?l=ja_JP">日文</a>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>