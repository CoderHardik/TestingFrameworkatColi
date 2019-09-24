package api_test;
import com.google.gson.JsonObject;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import static com.jayway.restassured.RestAssured.*;
import java.util.Scanner;

public class AuthenticationToken {
	
	/* Author : Hardik Shah
	 * To change between QA and prod simply change 'intelligence' to 'intelligence-qa' and vice versa
	 * To Do - Currently script asks for env twice as URL formation is happening in 2 class. Need to work on that so 
	 * entered parameter is used twice
	 */

	public static String Token() {
	JsonObject login = new JsonObject();
	String URL = AuthenticationToken.AuthEnvcheck();
	Scanner s = new Scanner(System.in);
	String username, password;
	
	System.out.println("Enter email address of user: ");
	username =s.nextLine();
	
	System.out.println("Enter Password: ");
	password = s.nextLine();
	
	login.addProperty("email", username);
	login.addProperty("password", password);
	
	
		String res =	given()
			.contentType(ContentType.JSON)
			.body(login.toString()).
			 when()
			.post(URL+"/ccmauth/authenticate").
			then()
			.extract().response().asString();
		
		JsonPath json = new JsonPath(res);
		String token = json.get("token");
		
		return token;
	
		//System.out.println(token);
	
	}//end of Token
	
	public static String Envcheck_input() {
		String env;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter environment you want to test--> Enter keyword 'QA' or 'PROD' or ''to proceed further: ");
		env = s.nextLine();
		return env;
	}//end of input of envcheck
	
	
	public static String Envcheck () {  //This method is to pass URL for ADAS endpoints
		String QA_URL ="https://adas-qa.ccmteam.com";
		String PROD_URL ="http://adas-int.ccmteam.com";
		String env_url;
		String res = AuthenticationToken.Envcheck_input();
		
		if(res.equals("QA")) {
			env_url=QA_URL;	
		}// end of if
		
		else if(res.equals("PROD")) {
			env_url=PROD_URL;
		}//end of else if
		
		else {
			env_url=null;
		}
		
		return env_url;
		
	}// end of env check
	
	
	public static String AuthEnvcheck () { //This method is to pass URL for non adas end point 
		
		String Auth_QA_URL="https://intelligence-qa.ccmteam.com";
		String Auth_PROD_URL="https://intelligence.collectivei.com";
		String Auth_env_url;
		String res = AuthenticationToken.Envcheck_input();
	
		
		if(res.equals("QA")) {
		
			Auth_env_url=Auth_QA_URL;
		}// end of if
		
		else if(res.equals("PROD")) {
			
			Auth_env_url=Auth_PROD_URL;
		}//end of else if
		
		else {
			Auth_env_url=null;
		}
		
		return Auth_env_url;
		
	}// end of env check
	
}
