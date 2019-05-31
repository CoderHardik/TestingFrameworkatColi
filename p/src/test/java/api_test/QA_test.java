package api_test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;
import api_test.AuthenticationToken;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat; 

import bsh.Token;
import api_test.UserTable;

import org.testng.Assert;
import  org.testng.Reporter;


import static com.jayway.restassured.RestAssured.*;


public class QA_test {

	String URL = AuthenticationToken.Envcheck();
	String token = AuthenticationToken.Token();
	String CI_ADAS_Filter_value = UserTable.get_ci_ADAS_filter().get("sdenton@collectivei.com"); //filter value for specific user
	String user_original_id_value = UserTable.user_original_id().get("sdenton@collectivei.com"); //user id value for specific user
	
	
	@Test(groups = {"Home Page"})
	public void getPipelineCount() throws InterruptedException{
	
		 given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/getOppScheduledActivities?oppid=na").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();	
		 
	}//end of pipeline count - Home page top left - Code response verified
	
	
	
	
	
}

