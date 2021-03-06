<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cybersoft.javabackend.crm.util.UrlConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body class="layout-login">
    <div class="layout-login__overlay"></div>
    <div class="layout-login__form bg-white" data-perfect-scrollbar>
        <div class="d-flex justify-content-center mt-2 mb-5 navbar-light">
            <a href="index.html" class="navbar-brand" style="min-width: 0">
                <img class="navbar-brand-icon" src="assets/images/logo.png" width="250" alt="Cybersoft">
            </a>
        </div>

        <h4 class="m-0">Sign up!</h4>
        <p class="mb-5">Create an account now</p>

        <form action="<c:url value="<%=UrlConst.AUTH_SIGNUP %>" />" method="post">
            <div class="form-group">
                <label class="text-label" for="name_2">Name:</label>
                <div class="input-group input-group-merge">
                    <input id="name_2" type="text" required="" class="form-control form-control-prepended" placeholder="John Doe">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="far fa-user"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="text-label" for="email_2">Email Address:</label>
                <div class="input-group input-group-merge">
                    <input id="email_2" type="email" required="" class="form-control form-control-prepended" placeholder="john@doe.com">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="far fa-envelope"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="text-label" for="password_2">Password:</label>
                <div class="input-group input-group-merge">
                    <input id="password_2" type="password" required="" class="form-control form-control-prepended" placeholder="Enter your password">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="far fa-key"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group mb-5">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" checked="" class="custom-control-input" id="terms" />
                    <label class="custom-control-label" for="terms">I accept <a href="#">Terms and Conditions</a></label>
                </div>
            </div>
            <div class="form-group text-center">
                <button class="btn btn-primary mb-2" type="submit">Create Account</button><br>
                <a class="text-body text-underline" href="<c:url value="<%=UrlConst.AUTH_LOGIN %>" />">Have an account? Login</a>
            </div>
        </form>
</body>