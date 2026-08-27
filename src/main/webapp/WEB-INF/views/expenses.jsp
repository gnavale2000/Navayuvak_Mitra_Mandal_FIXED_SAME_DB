<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html><html><head><title>Expenses</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><div class="container py-4"><h2>Mandal Expenses</h2>
<form method="post" action="/expenses/save" class="row g-2">
<div class="col-md-2"><input class="form-control" type="date" name="expenseDate" required></div>
<div class="col-md-2"><input class="form-control" name="category" placeholder="Category" required></div>
<div class="col-md-2"><input class="form-control" name="description" placeholder="Description"></div>
<div class="col-md-2"><input class="form-control" type="number" step="0.01" name="amount" placeholder="Amount" required></div>
<div class="col-md-2"><input class="form-control" name="paidTo" placeholder="Paid To"></div>
<div class="col-md-1"><select name="paymentMode" class="form-control"><option>Cash</option><option>UPI</option><option>Bank</option></select></div>
<div class="col-md-1"><button class="btn btn-danger">Save</button></div></form><hr>
<table class="table table-bordered"><tr><th>Date</th><th>Category</th><th>Description</th><th>Amount</th><th>Paid To</th><th>Mode</th></tr>
<c:forEach var="x" items="${expenses}"><tr><td>${x.expense_date}</td><td>${x.category}</td><td>${x.description}</td><td>₹ ${x.amount}</td><td>${x.paid_to}</td><td>${x.payment_mode}</td></tr></c:forEach></table>
<a href="/">Home</a></div></body></html>