public class HandlingSVGGraph {
 
	public static void main(String[] args) throws InterruptedException {
 
 
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		driver.get(httpswww.covid19india.org);
		
		
		Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath([@id=root]divdiv[2]div[1]div[4]div[2]divdiv[9]div[1]))).click();
		driver.findElement(By.xpath([@id=root]divdiv[2]div[1]div[4]div[2]divdiv[9]div[1])).click();
		
		ListWebElement graphPoints = driver.findElements(By.xpath(([name()='svg' and @preserveAspectRatio='xMidYMid meet'])[6][local-name()='circle']));
 
		Actions action = new Actions(driver);
		for(WebElement point graphPoints) {
			
			action.moveToElement(point).perform();
			System.out.println(driver.findElement(By.xpath(div[@class='stats is-confirmed']divh2)).getText());
			
			
		}
		
		
		
	}
 
}
