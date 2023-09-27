<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Captchas</title>
    <link href="/static/style.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div id="container">
    <div class="margin"></div>
    <div id="content">
        <h1>Please confirm that you're a robot</h1>
        <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
            <div><h2>Please solve the following challenges within <span id = "clock">${time}</span>
                </h2>
            </div>
            <div style="text-align: right">
                ${counter}
            </div>
        </div>
        <p><code class="codeblock">${chall}</code>
        </p>
        <form action="/" method="post" style="display: flex; align-items: center; justify-content: left">
            <label for="solution" style="margin: 10px">Solution:</label><br>
            <input type="text" id="solution" name="solution" class="input" style="margin: 10px"><br>
            <input type="submit" value="Submit" class="button" style="margin: 10px">
        </form>
        <code></code>
    </div>
    <div class="margin"></div>
</div>
<script src="/static/timer.js"></script>
</body>
</html>