<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html><html><head><title>Vargani</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><div class="container py-4"><h2>Vargani / Collection</h2>
<form method="post" action="/collections/save" class="row g-2">
<div class="col-md-2"><select name="memberId" class="form-control" required><c:forEach var="m" items="${members}"><option value="${m.id}">${m.name}</option></c:forEach></select></div>
<div class="col-md-2"><input class="form-control" name="receiptNo" placeholder="Receipt No"></div>
<div class="col-md-2"><input class="form-control" type="number" step="0.01" name="amount" placeholder="Amount" required></div>
<div class="col-md-2"><input class="form-control" type="date" name="collectionDate" required></div>
<div class="col-md-1"><select name="paymentMode" class="form-control"><option>Cash</option><option>UPI</option><option>Bank</option></select></div>
<div class="col-md-2"><input class="form-control" name="purpose" placeholder="Purpose"></div>
<div class="col-md-1"><button class="btn btn-success">Save</button></div></form><hr>
<table class="table table-bordered"><tr><th>Receipt</th><th>Member</th><th>Amount</th><th>Date</th><th>Mode</th><th>Purpose</th></tr>
<c:forEach var="x" items="${collections}"><tr><td>${x.receipt_no}</td><td>${x.member_name}</td><td>₹ ${x.amount}</td><td>${x.collection_date}</td><td>${x.payment_mode}</td><td>${x.purpose}</td></tr></c:forEach></table>
<a href="/">Home</a></div></body></html>