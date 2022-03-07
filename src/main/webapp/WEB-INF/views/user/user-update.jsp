<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	page import="cybersoft.javabackend.crm.util.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Update Profile User</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.USER_DASHBOARD%>" />">User
									Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Update Profile User</li>
						</ol>
					</nav>
					<h1 class="m-0">Update Profile User</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
			<div class="row no-gutters">
				<div class="col-lg-4 card-body">
					<p>
						<strong class="headings-color">Rules</strong>
					</p>
					<p class="text-muted">There is no rule!</p>
				</div>
				<div class="col-lg-8 card-form__body card-body">
					<form action="<c:url value="<%=UrlConst.USER_UPDATE%>" />"
						method="post">
						<div>
							<input type="hidden" class="form-control" name="id" id="id"
								value="${user.id }">
						</div>
						<div class="form-group">
							<label for="email">Email:</label> <input type="email"
								class="form-control" name="email" id="email"
								value="${user.email }">
						</div>
						<div class="form-group">
							<label for="password">Password:</label> <input type="password"
								class="form-control" name="password" id="password"
								value="${user.password }">
						</div>
						<div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" name="name" id="name"
                            	value="${user.fullname }">
                        </div>
                        <div class="form-group">
                            <label for="avatar">Avatar:</label>
                            <input type="text" class="form-control" name="avatar" id="phone"
                            	value="${user.avatar }">
                        </div>
                        <div class="form-group">
                                <label for="role">Role</label> 
                                <select id="role" name="role" data-toggle="select" class="form-control">
								<c:forEach var="role" items="${roles }">
									<c:choose>
									<c:when test="${user.role.id == role.id}">
									<option value="${role.id }" selected="">${role.name }</option>
									</c:when>
									
									<c:otherwise>
									<option value="${role.id }">${role.name }</option>
									</c:otherwise>
									</c:choose>
								</c:forEach>
								</select>
                            </div>
						<button class="btn btn-primary w-25 justify-content-center"
							type="submit" class="btn btn-primary">Update profile</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>