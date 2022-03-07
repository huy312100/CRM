<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cybersoft.javabackend.crm.util.UrlConst" %>    
<div class="page__header mb-0">
    <div class="container page__container">
        <div class="navbar navbar-secondary navbar-light navbar-expand-sm p-0">
            <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarsExample03" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapse" id="navbarsExample03">
                <ul class="nav navbar-nav flex">
                    <li class="nav-item">
                        <a class="nav-link active" href="<c:url value="<%=UrlConst.HOME %>" />">
                            Home
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            Job
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.JOB_DASHBOARD%>"/>">
                                Manage Job
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.JOB_ADD%>" />">
                                Create New Job
                            </a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            User
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.USER_DASHBOARD%>"/>">
                                Manage User
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.USER_ADD%>"/>">
                                Create User
                            </a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            Role
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.ROLE_DASHBOARD%>"/>">
                                Manage Role
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.ROLE_ADD%>"/>">
                                Create Role
                            </a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="<%=UrlConst.TASK_DASHBOARD %>" />">Task</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Components</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="buttons.html">Buttons</a>
                            <a class="dropdown-item" href="alerts.html">Alerts</a>
                            <a class="dropdown-item" href="avatars.html">Avatars</a>
                            <a class="dropdown-item" href="modals.html">Modals</a>
                            <a class="dropdown-item" href="charts.html">Charts</a>
                            <a class="dropdown-item" href="icons.html">Icons</a>
                            <a class="dropdown-item" href="forms.html">Forms</a>
                            <a class="dropdown-item" href="range-sliders.html">Range Sliders</a>
                            <a class="dropdown-item" href="datetime.html">Time &amp; Date</a>
                            <a class="dropdown-item" href="tables.html">Tables</a>
                            <a class="dropdown-item" href="tabs.html">Tabs</a>
                            <a class="dropdown-item" href="loaders.html">Loaders</a>
                            <a class="dropdown-item" href="drag.html">Drag &amp; Drop</a>
                            <a class="dropdown-item" href="pagination.html">Pagination</a>
                            <a class="dropdown-item" href="vector-maps.html">Vector Maps</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>