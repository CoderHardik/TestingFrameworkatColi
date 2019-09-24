package api_test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

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
	String user_guid_value=UserTable.user_guid().get("sdenton@collectivei.com");
	
	
	
	@Test(priority=0)
	public void getPipelineCount() throws InterruptedException{
	
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/getPipelineOppCount").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getPipelineCount api");
	}//end of pipeline count - Home page top left - Code response verified
	
	
}

