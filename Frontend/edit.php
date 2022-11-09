<!DOCTYPE html>

<?php require "lib/Api.php";
$path = $_GET["path"];

$doc = get_doc($path);
?>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>DocuServe</title>

        <link rel="stylesheet" href="css/edit.css">
    </head>
    <body>
        <h1>Editing: <?=$path?></h1>

        <table class="tableLayout">
            <td>
                <textarea id="mdArea"><?=$doc->content?></textarea>
            </td>

            <td>
                <button onclick="saveDoc('<?=$path?>')">Save</button>
                <button onclick="renderDoc('<?=$path?>')">Render</button>
            </td>

            <td class="renderedBox">
                <?=get_rendered($path)?>
            </td>
        </table>

        <script src="js/edit.js"></script>
    </body>
</html>