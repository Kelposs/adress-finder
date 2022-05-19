<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>住所検索</title>
<style>
ul {
	max-height: 400px;
	width: 300px;
	overflow: auto;
}

form {
	margin-left: 60px;
}

h2 {
	margin-left: 120px;
}

.column {
	float: left;
	width: 500px;
}

.row:after {
	content: "";
	display: table;
	clear: both;
}

.result-table {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
	position: absolute;
	top: 130px;
}

.result-table td, .result-table th {
	border: 1px solid #ddd;
	padding: 8px;
}

.result-table tr:nth-child(even) {
	background-color: #f2f2f2;
}

.result-table tr>td:hover {
	background-color: #ddd;
}

.result-table th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #04AA6D;
	color: white;
}
</style>
</head>
<body>
	<div class="row">
		<div class="column">
			<h2>住所検索</h2>
			<form action="hrsearch" method="POST">
				<input name="key">
				<button>検索</button>
			</form>
			<ul>

				<c:forEach var="e" items="${ result }">
					<li><a href="hrsearch?area=${ e.hashCode() }">${e.postal}</a></li>
				</c:forEach>
				<!-- <li><a href="hrsearch?area=${ result[0].hashCode() }"
></a></li> -->
			</ul>
		</div>
		<c:if test="${ selected != null }">
			<div class="column">
				<div class="result-table">
					<table>
						<tr>
							<th>都道府県名</th>
							<td>${ selected.prefecture }</td>
						</tr>
						<tr>
							<th>市区名</th>
							<td>${ selected.city }</td>
						</tr>
						<tr>
							<th>市区カナ</th>
							<td>${ selected.city_kana }</td>
						</tr>
						<tr>
							<th>町村名</th>
							<td>${ selected.town }</td>
						</tr>
						<tr>
							<th>町村名カナ</th>
							<td>${ selected.town_kana }</td>
						</tr>
						<tr>
							<th>郵便番号</th>
							<td>${ selected.postal }</td>
						</tr>
						<tr>
							<th>経度</th>
							<td>${ selected.x }</td>
						</tr>
						<tr>
							<th>緯度</th>
							<td>${ selected.y }</td>
						</tr>
					</table>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>