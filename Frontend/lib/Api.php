<?php

const API_URL="http://localhost:8080";

function get_docs(){
    return json_decode(file_get_contents(API_URL."/documents"));
}

function get_doc($path){
    return json_decode(file_get_contents(API_URL."/document?path=$path"));
}

function get_rendered($path){
    return json_decode(file_get_contents(API_URL."/render?path=$path"))->content;
}

?>