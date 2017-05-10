<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="../../common/tag.jsp" %>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Flow</title>
  <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  
      <link rel="stylesheet" href="<%=path%>/css/activity/flow.css">

  
</head>

<body>
  
<canvas class="canvas"></canvas><a class="codepen-link" href="http://www.codepen.io/seanfree" target="_blank"></a>
<div class="instructions"> 
  <p>Hover to repel particles</p>
  <p>&ensp;Mouse x / y position changes flow field frequencies</p>
  <p>Mouse down to attract particles</p>
  <p>Double-click to add an attractor</p>
  <p>Double-click attractors to remove</p>
</div>
<h2 class="count"></h2>
  <script src='https://rawgit.com/SeanFree/Vector2/master/Vector2.min.js'></script>
<script src='https://rawgit.com/josephg/noisejs/master/perlin.js'></script>

    <script src="<%=path%>/js/activity/flow.js"></script>

</body>
</html>
