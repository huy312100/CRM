<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cybersoft.javabackend.crm.util.UrlConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Login</title>
<body class="layout-login">
    <div class="layout-login__overlay"></div>
    <div class="layout-login__form bg-white" data-perfect-scrollbar>
        <div class="d-flex justify-content-center mt-2 mb-5 navbar-light">
            <a href="<c:url value="<%=UrlConst.HOME %>" />" class="navbar-brand" style="min-width: 0">
                <img class="navbar-brand-icon" src="assets/images/logo.png" width="250" alt="Cybersoft">
            </a>
        </div>

        <h4 class="m-0">Welcome back!</h4>
        <p class="mb-5">Login to access your account </p>
        <form action="<c:url value="<%=UrlConst.AUTH_LOGIN %>" />" method="post">
            <div class="form-group">
                <label class="text-label" for="email">Email Address:</label>
                <div class="input-group input-group-merge">
                    <input id="email" name="email" type="email" value="${email}" required="" class="form-control form-control-prepended" placeholder="contact@tuanphan.dev">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="far fa-envelope"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="text-label" for="password">Password:</label>
                <div class="input-group input-group-merge">
                    <input id="password" name="password" type="password" required="" class="form-control form-control-prepended" placeholder="Enter your password">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="fa fa-key"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group mb-5">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" name="rememberUsername" class="custom-control-input" checked="" id="remember">
                    <label class="custom-control-label" for="remember">Remember me</label>
                </div>
            </div>
            <div class="form-group text-center">
                <button class="btn btn-primary mb-5" type="submit">Login</button><br>
                <a href="" />" >Forgot password?</a> <br> Don't have an account? <a class="text-body text-underline" href="<c:url value="<%=UrlConst.AUTH_SIGNUP %>" />">Sign up!</a>
            </div>
        </form>
    </div>
</body>