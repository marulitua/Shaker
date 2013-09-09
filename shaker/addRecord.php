<?php
/**
 * File to handle all API requests
 * Accepts GET and POST
 *
 * Each request will be identified by TAG
 * Response will be JSON data
 
  /**
 * check for POST request
 */
if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];
 
    // include db handler
    require_once 'DB_Functions.php';
    $db = new DB_Functions();
 
    // response Array
    $response = array("tag" => $tag, "success" => 1, "error" => 0);
 
    // check for tag type
    if ($tag == 'insert') {
 
        $user = $db->tambahData($_POST['deviceId'], $_POST['x'], $_POST['y'], $_POST['z']);
        if ($user != false) {
            echo json_encode($response);
        } else {
            $response["success"] = 1;
            echo json_encode($response);
        }
    } 
    else {
    	//error 2 => Invalid Request
    	$response["error"] = 2;
    	echo json_encode($response);
    }
} else {
    //error 1 => Access Denied
    $response["error"] = 343;
    echo json_encode($response);
}
?>
