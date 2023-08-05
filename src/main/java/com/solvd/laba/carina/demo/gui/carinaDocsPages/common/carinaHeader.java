package com.solvd.laba.carina.demo.gui.carinaDocsPages.common;

import java.lang.invoke.MethodHandles;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class carinaHeader extends AbstractUIObject {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@FindBy(xpath = "./nav/div[1]/div/span[1]")
	private ExtendedWebElement carinaTextSpan;
	
	@FindBy(xpath = "./nav/div[2]/div/form/input")
	private ExtendedWebElement searchBar;
	
	@FindBy(xpath = "./nav/div[3]/a")
	private ExtendedWebElement gitHubLink;
	
	@FindBy(css = "a.md-header-nav__button.md-logo")
	private ExtendedWebElement overviewImage;
	
	public carinaHeader(WebDriver driver, SearchContext searchContext) {
	    super(driver, searchContext);
	}

	public ExtendedWebElement getCarinaTextSpan() {
		return carinaTextSpan;
	}

	public void setCarinaTextSpan(ExtendedWebElement carinaTextSpan) {
		this.carinaTextSpan = carinaTextSpan;
	}

	public ExtendedWebElement getSearchBar() {
		return searchBar;
	}

	public void setSearchBar(ExtendedWebElement searchBar) {
		this.searchBar = searchBar;
	}

	public ExtendedWebElement getGitHubLink() {
		return gitHubLink;
	}

	public void setGitHubLink(ExtendedWebElement gitHubLink) {
		this.gitHubLink = gitHubLink;
	}
	
	public ExtendedWebElement getOverviewImage() {
		return overviewImage;
	}

	public void setOverviewImage(ExtendedWebElement overviewImage) {
		this.overviewImage = overviewImage;
	}

	public void clickOverViewImage() {
		LOGGER.info("clicking logo image");
		this.overviewImage.click();
	}
}