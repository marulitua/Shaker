<?php

require_once('DB_Functions.php');

$db = new DB_Functions();

$result = $db->queryAll();

echo '<pre>';
	 
	 while ($row = mysql_fetch_assoc($result)) {
    	      print_r($row);
	 }


echo '</pre>';

?>
