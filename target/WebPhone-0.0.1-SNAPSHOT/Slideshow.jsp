<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of slideshow-->
<html>
<head>
<title>Slide Show</title>
<meta charset='UTF-8'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<style type="text/css">
.demoslide {
	margin-top: 100px;
	width: 100%;
	margin: 0 auto;
	height: 300px;
	overflow: hidden;
}
</style>
</head>
<script type="text/javascript">
	$(function() {
		$('.demoslide img:gt(0)').hide();
		setInterval(function() {
			$('.demoslide :first-child').fadeOut() //FadeOut là ảnh đang hiện
			.next('img').fadeIn() //fadeIn ảnh tiếp theo
			.end().appendTo('.demoslide'); // chuyển vị trí ảnh xuống cuối
		}, 5000);
	})
</script>
<body>
	<div class="demoslide">
		<img src="image/a1.png" width= 100% height='300px'> 
		<img src="image/a2.png" width= 100% height='300px'> 
		<img src="image/a3.png" width= 100% height='300px'>
	</div>
</body>
</html>

