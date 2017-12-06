/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//var SERVICE_URL = "http://80.211.148.213:8080/myapp/skills/";
var SERVICE_URL = "http://localhost:8080/myapp/skills/";

function showMessage(success,msg) {
	$( ".dynamicboxes" ).hide( "medium", function() {});
	if (success) {
		$( "#success-box" ).text(msg);
		$( "#success-box" ).show( "medium", function() {});
	} else {
		$( "#warning-box" ).text(msg);
		$( "#warning-box" ).show( "medium", function() {});
	}
}
