<!DOCTYPE html>

<?php require "lib/Api.php"; ?>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>DocuServe</title>
    </head>
    <body>
        <h1>DocuServe</h1>

        <table>
            <th>ID</th>
            <th>Link</th>

            <?php foreach (get_docs() as $doc): ?>
            <tr>
                <td><?=$doc->path?></td>
                <td><a href="/edit.php?path=<?=$doc->path?>">Go</a></td>
            </tr>
            <?php endforeach ?>
        </table>

        <script src="js/index.js"></script>
    </body>
</html>