package api_test;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import api_test.AuthenticationToken;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import  org.testng.Reporter;

/* Author - Hardik Shah
 * For switching between QA and PROD do following
 * 1. Change 'adas' to 'adas-qa' - simply do find and replace
 * 2. Change 'ccmteam' to 'collectivei' - Here also do find and replace
 * Above items 1,2 are taken care by declaring global string with that parametr so one have to just change the string every time they execute
 * 3.CI ADAS filter (in get connection count) and user_ID in calendar requires unique ID for every user, That can not be hard coded in code
 * Above is taken care by declaring HashTables in User Table class. email points to specific id. Add ids for different user and here just enter the key - which is email address of user
 * 4. User name and Password were hardcoded in Authentication Token class. 
 * Changed it to  user input type so we could add while running test
 * 5. For env, I changed URL variables for QA and PROD but still it was hard coded 
 * Added a method in Authentication Token to take user input of QA or PROD and make selection accordingly
 * 6. Made token and URL as global variable so it does not ask for username and password for every test case
 */

public class GetnetworkFeed {
		
	String URL = AuthenticationToken.Envcheck();
	String token = AuthenticationToken.Token();
	String CI_ADAS_Filter_value = UserTable.get_ci_ADAS_filter().get("sdenton@collectivei.com"); //filter value for specific user
	String user_original_id_value = UserTable.user_original_id().get("sdenton@collectivei.com"); //user id value for specific user
	String user_guid_value=UserTable.user_guid().get("sdenton@collectivei.com");
	
	
	@Test(priority=0)
	public void getNewsFeed()throws InterruptedException {
		
		Reporter.log("Network feed is CALLED"); // This will print result in output file
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/homepagefeed").
		then()
		.statusCode(200)
		//.time(lessThan(5000L))   // This is for response time- will throw error if response not responding within 5 seconds
		.extract().response().prettyPrint();
		Reporter.log("Network feed WAS Call complete");
	}// end of news feed - Home page -  Code response verified
	
	
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
	
	
	
	@Test(groups = {"Home Page"})
	public void getPipelineCountCheckifValueisgreaterthanzero() throws InterruptedException{
	
		String res= given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/getPipelineOppCount").
		then()
		.extract().response().asString();  
		
		System.out.println(res);
		JsonPath json = new JsonPath(res);
		String opp = json.get("result.opportunities");
		
		JsonPath json_res = new JsonPath(opp); // Had to do extraction twice as I only get null value if I do in first attempt.
		int opp_count = json_res.get("data.open_opp_count");//So extracted full response and then extract response within it. Even third party site was also extracting null so something in response we have
		
		assertThat(opp_count,greaterThan(0));
			
		 
	}//end of pipeline count - Home page top left - Code response verified
	
	
	
	
	@Test(priority=0)
	public void getConnectionCount() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token)
		.header("ccm_module","analysisId")
		.header("CI_ADAS_Filter",CI_ADAS_Filter_value).// This is for Steve Denton, Change key value and add as we gather more users
		when()
		.get(URL+"/analytics/rest/v2/analysis/myconnections").
		then()
		.extract().response().prettyPrint();
		System.out.println("getConnectionCount api");
	}//end of connections count - Home page top left - code response validated
	
	
	@Test(priority=0)
	public void getForecastteamSummary() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/forecastteamsummary").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getForecastteamSummary api");
	}//end of forecast summary - Home page forecast summary - code response validated
	
	@Test(priority=0)
	public void getForecastQuarterSummary() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token)
		.header("analysisId", "Home forecast")
		.queryParam("timePeriod", "QUARTER").
		when()
		.get(URL+"/analytics/rest/v2/analysis/forecastsummary").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getForecastQuarterSummary api");
	}//end of forecast summary - Home page forecast summary - code response validated- * Add query param at other places like this
	
	@Test(priority=0)
	public void getForecastMonthSummary() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token)
		.header("analysisId", "Home forecast").
		when()
		.get(URL+"/analytics/rest/v2/analysis/forecastsummary?timePeriod=MONTH").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getForecastMonthSummary api");
	}//end of forecast summary - Home page forecast summary - code response validated
	
	@Test(priority=0)
	public void getForecastYearSummary() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token)
		.header("analysisId", "Home forecast").
		when()
		.get(URL+"/analytics/rest/v2/analysis/forecastsummary?timePeriod=YEAR").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getForecastYearSummary api");
	}//end of forecast summary - Home page forecast summary - code response validated
	
	@Test(priority=0)
	public void getCalendarInfo() throws InterruptedException{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/getTodayCalendarInfo?calendar_today_date="+dtf.format(localDate)+"&user_original_id="+user_original_id_value).
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getCalendarInfo api");
	}//end of Calendar info - Home page calendar - code response validated ** user id is used when we only need info for within tenant **
	
	@Test(priority=0)
	public void getTodateTimeperiods() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/metadata/todatetimeperiods").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getTodateTimeperiods api");
		
	}//end of To date time periods - code response validated

	@Test(priority=0)
	public void getTrailingTimeperiods() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/metadata/trailingtimeperiods").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getTrailingTimeperiods api");
	}//end of Trailing date time periods - These returns simple time periods - code response verified
	
	@Test(priority=0)
	public void getForecastTimeperiods() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/metadata/forecasttimeperiods").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getForecastTimeperiods api");
	}//end of Forecast date time periods - These returns simple time periods - code response verified

	@Test(priority=0)
	public void getTopdeals() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/getTopDeals").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getTopdeals api");
	}// end of Top deals - Home page top deals - code response verified
	
	@Test(priority=0)
	public void getRecentRiskCounts() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/recentriskcount").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getRecentRiskCounts api");
	}// end of Recent Risk Count - home page risk counts - code response verified
	
	@Test(priority=0)
	public void getMyTeamInfo() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/metadata/myteaminfo").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getMyTeamInfo api");
	}// end of My team info - Home page my teams - code response verified
	
	@Test(priority=0) // This test case will only work for user who has opportunity with scheduled activity - It will fail if that is not the case
	public void getOppScheduledActivity() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/getOppScheduledActivities?oppid=000OppX5c8ecc64fe75b837afd46e70").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getOppScheduledActivity api home page");
	}// end of Opp Scheduled Activity - code response verified
	
	
	@Test(priority=0)
	public void getUserProfileNetworkinsights() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token)
		.header("ccm_module","analysisId")
		.header("CI_ADAS_Filter",CI_ADAS_Filter_value).
		when()
		.get(URL+"/analytics/rest/v2/analysis/userprofilenetworkinsights?timePeriod=QTD").
		then()
		.extract().response().prettyPrint();
		System.out.println("getUserProfileNetworkinsights api");
	}// end of User Profile Network insights - code response verified
	
	
	@Test(priority=0)
	public void getPipelineSummary() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinesummary").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getPipelineSummary api");
	}// end of Pipeline Summary - pipeline page - code response verified
	
	
	@Test(priority=0)
	public void getSellerStage() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/metadata/sellerstages").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getSellerStage api");
	}// end of Seller Stage - pipeline page - code response verified
	
	@Test(priority=0)
	public void getBuyerroles() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/metadata/buyerroles").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getBuyerroles api");
	}// end of Opp Activity Stage Details - pipeline page - opportunity page - code response verified
	
	@Test(priority=0)
	public void getOppActivityStagedetails() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/oppactivitystagedetails?oppid=000OppX5ae9d5c15339914c6b434460").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getOppActivityStagedetails api");
	}// end of Opp Activity Stage Details - pipeline page - opportunity page - code response verified
	
	
	
	@Test(priority=0)
	public void getCumulativerevenue() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/cumulativerevenue?timePeriod=QUARTER").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getCumulativerevenue api");
	}// end of cumulative revenue - code response verified - Predictive Team Forecast
	
	@Test(priority=0)
	public void getCumulativerevenueforDay() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/cumulativerevenueforday").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("getCumulativerevenueforDay api");
	}// end of cumulative revenue for day - code response verified - Predictive Team Forecast
	
	@Test(priority=0)
	public void getCumulativerevenueforecast() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/revenueforecast?timePeriod=QTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of cumulative revenue forecast - code response verified- Predictive Team Forecast
	
	@Test(priority=0)
	public void getExpectedGrowth() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/expectedgrowth").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Expected Growth - code response verified
	
	@Test(priority=0)
	public void getSalesSummaryMeasures() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/salessummarymeasures?timePeriod=QTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Sales Summary Measures - code response verified
	
	@Test(priority=0)
	public void getDescendRevenue() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/descendantrevenue?timePeriod=QUARTER").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Descend Revenue - Forecast - individual forecast revenue - code response verified
	
	
	@Test(priority=0)
	public void getHeatMapPrabability() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/heatmap?view=probability").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Heat Map - Pipeline Insights - probability - code response verified
	
	@Test(priority=0)
	public void getHeatMapAge() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/heatmap?view=age").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Heat Map - Pipeline Insights - Age - code response verified
	
	@Test(priority=0)
	public void getHeatMapStage() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/heatmap?view=stage").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Heat Map - Pipeline Insights - Age - code response verified
	
	
	@Test(priority=0)
	public void getPipelineMonitor() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token)
		.header("analysisId", "Pipeline monitor").
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinemonitor?timePeriod=90D").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
		System.out.println("Pipeline monitor api");
	}// end of Pipeline Monitor - Team Progress - - code response verified
	
	@Test(priority=0)
	public void getPipelineMonitorMeasure() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token)
		.header("analysisId", "Pipeline monitor").
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinemonitorkpimeasures").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Monitor - Team Progress - Another api - code response verified
	
	@Test(priority=0)
	public void getPipelineVolumeWeekly() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinevolume?timePeriod=7D").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineVolumeMonthly() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinevolume?timePeriod=30D").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineVolumeQuarter() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinevolume?timePeriod=90D").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineVolumeYearly() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinevolume?timePeriod=12M").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow
	
	@Test(priority=0)
	public void getPipelineVolumeWeekToDate() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinevolume?timePeriod=WTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineVolumeMonthToDate() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinevolume?timePeriod=MTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineVolumeQuarterToDate() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinevolume?timePeriod=QTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineVolumeYearToDate() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinevolume?timePeriod=YTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - Year to date
	
	
	@Test(priority=0)
	public void getPipelineChangeWeekly() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinechange?timePeriod=7D").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineChangeMonthly() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinechange?timePeriod=30D").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineChangeQuarter() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinechange?timePeriod=90D").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineChangeYearly() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinechange?timePeriod=12M").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow
	
	@Test(priority=0)
	public void getPipelineChangeWeekToDate() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinechange?timePeriod=WTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineChangeMonthToDate() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinechange?timePeriod=MTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineChangeQuarterToDate() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinechange?timePeriod=QTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - code response verified
	
	@Test(priority=0)
	public void getPipelineChangeYearToDate() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/pipelinechange?timePeriod=YTD").
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of Pipeline Volume - Pipeline Flow - Year to date
	
	
	
	@Test(priority=0)
	public void getUserDetails() throws InterruptedException{
		
		given()
		.header("Authorization", "Bearer "+token).
		when()
		.get(URL+"/analytics/rest/v2/analysis/userdetails?user_guid="+user_guid_value). 
		then()
		.statusCode(200)
		.extract().response().prettyPrint();
	}// end of user profile- This is person self profile information - code response verified
	
}//end of class
