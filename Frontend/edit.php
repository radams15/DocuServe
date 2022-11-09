<!DOCTYPE html>

<?php require "lib/Api.php";
$id = $_GET["id"];

$doc = get_doc($id);
?>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>DocuServe</title>
    </head>
    <body>
        <h1>Editing: <?=$id?></h1>

        <textarea><?=$doc->content?></textarea>

        <script src="js/index.js"></script>
    </body>
</html>