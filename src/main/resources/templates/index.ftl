<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="/static/style.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<div>
    <h1>Please confirm that you're a robot</h1>
    <h2>Please solve the following challenges within <span id = "remaining time"></span> ${counter}</h2>
    <p><code>${chall}</code>
    </p>
    <form action="/" method="post">
        <label for="solution">Solution:</label><br>
        <input type="text" id="solution" name="solution"><br>
        <input type="submit" value="Submit">
    </form>
    <code></code>
</div>
</body>
</html>