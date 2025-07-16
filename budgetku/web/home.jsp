<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="budgetku.User" %>
<%@ page import="budgetku.Budget" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Budgetku - App</title>
    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Icons -->
    <script src="https://unpkg.com/@phosphor-icons/web"></script>
</head>
<body>
    <header class="header-container">
        <div class="logo-title">
            <img src="img-budget/bag.png" alt="icon kantung uang">
            <h1>Budgetku.</h1>
        </div>
    </header>

    <section class="greeting">
        <h2>Selamat datang, <span class="highlight-name">${sessionScope.username} !</span></h2>
        <a href="logout" class="logout-btn" title="Logout">
            <i class="ph-fill ph-sign-out"></i>
        </a>
    </section>
    
    <main id="budgets" class="container">
        <c:out value="${list}" />
        <c:choose>
            <c:when test="${empty list}">
                <div class="empty-state">
                    <h3>Belum ada daftar</h3>
                    <p>Silakan tambah budget untuk memulai</p>
                </div>
            </c:when>
            
            <c:otherwise>
                <c:forEach var="budget" items="${list}">
                    <div class="budget__card">
                        <h2 class="budget__name">${budget.kategori}</h2>
                        <p class="budget__amount">${budget.total_budget}</p>
                        <p class="budget__total">Total Rp 500.000</p>
                    </div>
                </c:forEach>
            </c:otherwise>    
        </c:choose>
        
    <!-- Tombol Tambah Budget -->
    <button class="add__budget__btn" onclick="openModal()">+</button>
    </main>
    
    <!-- Modal Form Budget -->
    <div id="budget_form" class="modal hidden">
        <div class="card">
            <div class="modal__card__heading">
                <h4>Tambah Budget</h4>
                <i class="ph-fill ph-x-circle" onclick="closeModal()"></i>
            </div>

            <form action="add-budget" method="post">
                <label for="kategori">Nama Budget</label>
                <input name="kategori" type="text" required />

                <label for="total">Jumlah Budget</label>
                <div class="input__money">
                    <span>Rp</span>
                    <input name="total" type="number" step="0.01" required />
                </div>

                <button type="submit">Simpan</button>
            </form>
        </div>
    </div>
    

    <script>
        function openModal() {
            document.getElementById("budget_form").classList.remove("hidden");
        }

        function closeModal() {
            document.getElementById("budget_form").classList.add("hidden");
        }
    </script>

</body>
</html>
