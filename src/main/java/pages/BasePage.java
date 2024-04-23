package pages;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public abstract class BasePage {

    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }


    //*** For taking screenshot
    public String takeScreenShot(String imageName){
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String imagePath = "resources/screenshot/" + imageName + ".png";

        try {
            Files.move(screenshot, new File(imagePath));
            return imagePath;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
