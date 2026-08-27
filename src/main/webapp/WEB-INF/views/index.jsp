<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html><html><head><title>Navyuvak Mitra Mandal</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="bg-light"><div class="container py-4">
<h1>Navyuvak Mitra Mandal</h1><p>Navalemala, Babhulwade, Parner - 414302</p>
<div class="row g-3">
<div class="col-md-4"><div class="card p-4"><h5>Total Sadasya</h5><h2>${members}</h2></div></div>
<div class="col-md-4"><div class="card p-4"><h5>Total Vargani</h5><h2>₹ ${collection}</h2></div></div>
<div class="col-md-4"><div class="card p-4"><h5>Total Expense</h5><h2>₹ ${expense}</h2></div></div></div>
<hr>
<a class="btn btn-primary" href="/members">Sadasya Mahiti</a>
<a class="btn btn-success" href="/collections">Vargani</a>
<a class="btn btn-danger" href="/expenses">Expense</a>
<a class="btn btn-dark" href="/report">Report</a>
</div></body></html>