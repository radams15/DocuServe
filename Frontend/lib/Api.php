<?php

const API_URL="http://localhost:8080";

function get_docs(){
    return json_decode(file_get_contents(API_URL."/documents"));
}

function get_doc($id){
    return json_decode(file_get_contents(API_URL."/document?id=$id"));
}

?>