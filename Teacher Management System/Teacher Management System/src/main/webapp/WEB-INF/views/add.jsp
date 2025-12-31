<!DOCTYPE html>
<html>
<head>
    <title>Add Department</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="bg-page">
    <div class="container">
        <h1>Add New Department</h1>

        <form id="addForm">
            <input type="text" id="name" placeholder="Teacher Name" required>
            <input type="text" id="location" placeholder="Subject" required>
            <input type="number" id="budget" placeholder="Experience" required>
            <button type="submit">Add Teacher</button>
        </form>

        <a href="${pageContext.request.contextPath}/">Back to Home</a>
        <p id="msg"></p>
    </div>

    <script>
        document.getElementById('addForm').onsubmit = function(e) {
            e.preventDefault();

            fetch('${pageContext.request.contextPath}/departments', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    name: document.getElementById('name').value,
                    location: document.getElementById('location').value,
                    budget: document.getElementById('budget').value
                })
            })
            .then(res => res.text())
            .then(msg => {
                document.getElementById('msg').innerHTML = msg;
                document.getElementById('addForm').reset();
            });
        };
    </script>
</body>
</html>
