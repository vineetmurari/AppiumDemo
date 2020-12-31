package androidTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidTest1 {	

	
	
	    
public static void main(String[] args) throws Exception {
	
	  try
      { 
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"emulator -avd Emulator\""); 
      } 
      catch (Exception e) 
      { 
          System.out.println("HEY Buddy ! U r Doing Something Wrong "); 
          e.printStackTrace(); 
      } 
	    
	
	  AndroidDriver<AndroidElement>  driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("BROWSER_NAME", "Android");
	capabilities.setCapability("VERSION", "5.0.4"); 
	capabilities.setCapability("deviceName","Emulator");
	capabilities.setCapability("platformName","Android");
	capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
 
   
   capabilities.setCapability("appPackage", "com.vinmurari.AwesomeProject");
// This package name of your app (you can get it from apk info app)
	capabilities.setCapability("appActivity","com.vinmurari.AwesomeProject.MainActivity"); // This is Launcher activity of your app (you can get it from apk info app)
//Create RemoteWebDriver instance and connect to the Appium server
 //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
   driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
   //locate the Text on the calculator by using By.name()
   Thread.sleep(10000);
   List<AndroidElement> elements = driver.findElements(By.xpath("//android.widget.EditText"));
   
   elements.get(0).sendKeys("Online_User");
   elements.get(1).sendKeys("Online_User");
  
   driver.navigate().back();
   
   WebElement signin=driver.findElement(By.xpath("//android.widget.TextView[@text='SIGN IN']"));
   signin.click();

 driver.findElementByAndroidUIAutomator(
         "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Fresh Orange\"));");
 driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Apple\").instance(0))"); 

   driver.quit();
   
   ArrayList<String> CMD_PID = new ArrayList<String>();
   
   String KILL = "taskkill /IM ";
   
   Runtime.getRuntime().exec(KILL + "qemu-system-i386.exe");
   
   Thread.sleep(4000);
   
   String TASKLIST = "tasklist /fi \"imagename eq cmd.exe\" /fo list /v ";
   
   Process p1 = Runtime.getRuntime().exec(TASKLIST);
   BufferedReader reader = new BufferedReader(new InputStreamReader(
     p1.getInputStream()));
   String line;
   while ((line = reader.readLine()) != null) {
    if(line.contains("Window") || line.contains("PID"))	{
	   CMD_PID.add(line);
    }
   }
   
   String KILL_PID ="Taskkill /PID %1$s /F ";

   for(int i =0; i<CMD_PID.size();i++){
	if(CMD_PID.get(i).contains("Emulator")){   
   String killpid=String.format(KILL_PID,CMD_PID.get(i-1).replace("PID", "").replace(":", "").trim());
   Runtime.getRuntime().exec(killpid );
   Thread.sleep(5000);
   System.out.println("DONE 1");
}
   }


// Runtime.getRuntime().exec(KILL + "cmd.exe");
  
}

}
