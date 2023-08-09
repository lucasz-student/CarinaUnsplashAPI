package com.solvd.laba.carina.demo.gui.carinaDocsPages.common;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class carinaSideNavigationBar extends AbstractUIObject {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final String CARINA_HIGHLIGHTED_LINK_COLOR = "rgb(6, 194, 126)";
	
	@FindBy(xpath = "./label")
	private ExtendedWebElement carinaLabel;
	
	@FindBy(xpath = "./ul//li")
	private List<ExtendedWebElement> navBarLinks;
	
	@FindBy(xpath = "./ul//li/a")
	private List<ExtendedWebElement> navBarLinksTexts;
	
	@FindBy(css = ".md-nav__item--nested")
	private List<ExtendedWebElement> navBarParentOfHiddenLinks;
	
	@FindBy(css = ".md-nav__item--nested > nav") 
	private List<ExtendedWebElement> hiddenNavBars;
	
	@FindBy(css = "li.md-nav__item--nested nav:nth-child(3) > ul> li > a")
	private List<ExtendedWebElement> navBarHiddenLinksTexts; 
	
	public carinaSideNavigationBar(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}
	
	public String getLabelText() {
		return carinaLabel.getText();
	}
	
	public int getNavBarLinksSize() {
		return navBarLinks.size();
	}
	
	public boolean checkIfFirstLinkisHighlighted() {
		String OverviewColor = navBarLinksTexts.get(0).getElement().getCssValue("color");
		LOGGER.info("link color : " + OverviewColor );
		if (OverviewColor.equals(CARINA_HIGHLIGHTED_LINK_COLOR)) {
			return true;
		}
		return false;
	}
	
	public void clickParentLinks() {
		navBarParentOfHiddenLinks.forEach((l) -> {
			l.click();
		});
	}
	
	public boolean checkVisibilityOfHiddenLinks() {
		ArrayList<Boolean> displayedStatuses = new ArrayList<>();
		navBarHiddenLinksTexts.forEach((hl) -> {
			displayedStatuses.add(hl.getElement().isDisplayed());
		});
		
		if (displayedStatuses.contains(false)) {
			return false;
		}
		return true; 
	}
	
	public boolean clickLinksAndValidateColors() {
		clickParentLinks();
		ArrayList<Boolean> linkColors = new ArrayList<>();
		navBarHiddenLinksTexts.forEach((l) -> {
			l.click();
			String linkColor = l.getElement().getCssValue("color");
			LOGGER.info("link color: " +  linkColor);
			if (linkColor.equals(CARINA_HIGHLIGHTED_LINK_COLOR)) {
				linkColors.add(true);
			} else {
				linkColors.add(false);
			}
			
		});
		if (linkColors.contains(false)) {
			return false;
		} else {
			return true;
		}
	}
}