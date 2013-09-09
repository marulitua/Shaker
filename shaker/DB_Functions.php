<?php
 
class DB_Functions {
 
    private $db;
 
    //put your code here
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Storing new record
     */
    public function tambahData($deviceId, $x, $y, $z) {

	$sql = "insert into records value ('', '$deviceId', TIMESTAMP(now()), $x,$y, $z)";
      	//echo $sql;
        $result = mysql_query($sql);
        // check for successful store
        if ($result) {
            return true;
        } else {
            return false;
        }
    }
 
    /*
    query all data
    */
    public function queryAll(){
    
    	 $result = mysql_query("SELECT * FROM records");
	
         return $result;
      
    }
}
?>
