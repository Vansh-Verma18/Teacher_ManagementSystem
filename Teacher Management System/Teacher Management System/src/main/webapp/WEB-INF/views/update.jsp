<!DOCTYPE html>
<html>
<head>
    <title>Update Department</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="bg-page">
    <div class="container">
        <h1>Update Department</h1>

        <form id="updateForm">
            <input type="number" id="id" placeholder="Teacher ID" required>
            <input type="text" id="name" placeholder="New Name" required>
            <input type="text" id="location" placeholder="New Subject" required>
            <input type="number" id="budget" placeholder="New Experience" required>
            <button type="submit">Update Teacher</button>
        </form>

        <a href="${pageContext.request.contextPath}/">Back to Home</a>
        <p id="msg"></p>
    </div>

    <script>
        document.getElementById('updateForm').onsubmit = function(e) {
            e.preventDefault();

            let id = document.getElementById('id').value;

            fetch('${pageContext.request.contextPath}/departments/' + id, {
                method: 'PUT',
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
                document.getElementById('updateForm').reset();
            });
        };
    </script>
</body>
</html>
