<!DOCTYPE html>
<html>
<head>
    <title>Delete Department</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="bg-page">
    <div class="container">
        <h1>Delete Department</h1>

        <form id="deleteForm">
            <input type="number" id="id" placeholder="Teacher ID" required>
            <button type="submit">Delete Teacher</button>
        </form>

        <a href="${pageContext.request.contextPath}/">Back to Home</a>
        <p id="msg"></p>
    </div>

    <script>
        document.getElementById('deleteForm').onsubmit = function(e) {
            e.preventDefault();

            let id = document.getElementById('id').value;

            fetch('${pageContext.request.contextPath}/departments/' + id, {
                method: 'DELETE'
            })
            .then(res => res.text())
            .then(msg => {
                document.getElementById('msg').innerHTML = msg;
                document.getElementById('deleteForm').reset();
            });
        };
    </script>
</body>
</html>
