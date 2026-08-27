<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html><html><head><title>Sadasya</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><div class="container py-4"><h2>Sadasya Mahiti</h2>
<form method="post" action="/members/save" class="row g-2">
<div class="col-md-2"><input class="form-control" name="name" placeholder="Name" required></div>
<div class="col-md-2"><input class="form-control" name="mobile" placeholder="Mobile"></div>
<div class="col-md-3"><input class="form-control" name="address" placeholder="Address"></div>
<div class="col-md-2"><input class="form-control" type="date" name="joiningDate"></div>
<div class="col-md-2"><input class="form-control" name="role" placeholder="Role"></div>
<div class="col-md-1"><button class="btn btn-primary">Save</button></div></form><hr>
<table class="table table-bordered"><tr><th>ID</th><th>Name</th><th>Mobile</th><th>Address</th><th>Joining</th><th>Role</th></tr>
<c:forEach var="x" items="${members}"><tr><td>${x.id}</td><td>${x.name}</td><td>${x.mobile}</td><td>${x.address}</td><td>${x.joining_date}</td><td>${x.role}</td></tr></c:forEach>
</table><a href="/">Home</a></div></body></html>