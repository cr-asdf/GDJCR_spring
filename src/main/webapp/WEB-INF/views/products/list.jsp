<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>
</head>
<body>
<c:import url="/WEB-INF/views/templates/layout_header.jsp"></c:import>

<div class="continer-fluid my-5">
	<div class="row col-md-8 offset-md-2" style="min-height: 60vh" >
		<!-- contents 내용 작성 -->
		<h1>Product List Page</h1>
		
		<form id="list_form" action="./list"  class="row row-cols-lg-auto g-3 align-items-center">
			<input type="hidden" name="page" id="pageNum">
		  <div class="col-12">
		    <label class="visually-hidden" for="inlineFormSelectPref">Preference</label>
		    <select name="kind" class="form-select" id="inlineFormSelectPref">
		      <option value="k1" ${pager.kind eq 'k1' ? 'selected': ''}>제목</option>
		      <option value="k2" ${pager.kind eq 'k2' ? 'selected': ''}>내용</option>
		      <option value="k3" ${pager.kind eq 'k3' ? 'selected': ''}>제목+내용</option>
		    </select>
		  </div>
		  
		  <div class="col-12">
		    <label class="visually-hidden" for="inlineFormInputGroupUsername">Username</label>
		      <input type="text" value="${pager.search}" name="search" class="form-control" id="inlineFormInputGroupUsername" placeholder="검색어 입력">
		  </div>		

		
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary">Submit</button>
		  </div>
		</form>
		
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Num</th>
					<th>상품명</th>
					<th>이자율</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="v">
				<tr>
					<td>${v.productNum}</td>
					<td><a href="./detail?productNum=${v.productNum}">${v.productName}</a></td>
					<td>${v.productRate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">
		      <button class="page-link pages" data-page-num="${pager.start-1}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
			  </button>
		    </li>
		    <c:forEach begin="${pager.start}" end="${pager.end}" var="i">
		    <li class="page-item">
				<button class="page-link pages" data-page-num="${i}">${i}</button></li>
		    </c:forEach>
		    <li class="page-item ${pager.endCheck?'disabled':''}">
		      <button class="page-link pages" data-page-num="${pager.end+1}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
			  </button>
		    </li>
		  </ul>
		</nav>
		
		
		<div>
		<a href="./add" class="btn btn-outline-success">상품 등록</a>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/views/templates/layout_footer.jsp"></c:import>
<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
<script src="/resources/js/list.js"></script>
</body>
</html>