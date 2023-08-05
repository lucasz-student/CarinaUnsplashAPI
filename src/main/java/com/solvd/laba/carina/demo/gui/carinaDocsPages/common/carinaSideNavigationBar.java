package com.solvd.laba.carina.demo.gui.carinaDocsPages.common;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class carinaSideNavigationBar extends AbstractUIObject {

	@FindBy(xpath = "./label")
	private ExtendedWebElement carinaLabel;
	
	@FindBy(xpath = "./ul//li")
	private List<ExtendedWebElement> navBarLinks;
	
	@FindBy(css = ".md-nav__item--nested")
	private List<ExtendedWebElement> navBarParentOfHiddenLinks;
	
	@FindBy(css = ".md-nav__item--nested nav.md-nav") 
	private List<ExtendedWebElement> navBarHiddenLinks;
	
	public carinaSideNavigationBar(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	public ExtendedWebElement getCarinaLabel() {
		return carinaLabel;
	}

	public void setCarinaLabel(ExtendedWebElement carinaLabel) {
		this.carinaLabel = carinaLabel;
	}

	public List<ExtendedWebElement> getNavBarLinks() {
		return navBarLinks;
	}

	public void setNavBarLinks(List<ExtendedWebElement> navBarLinks) {
		this.navBarLinks = navBarLinks;
	}

	public List<ExtendedWebElement> getNavBarParentOfHiddenLinks() {
		return navBarParentOfHiddenLinks;
	}

	public void setNavBarParentOfHiddenLinks(List<ExtendedWebElement> navBarParentOfHiddenLinks) {
		this.navBarParentOfHiddenLinks = navBarParentOfHiddenLinks;
	}

	public List<ExtendedWebElement> getNavBarHiddenLinks() {
		return navBarHiddenLinks;
	}

	public void setNavBarHiddenLinks(List<ExtendedWebElement> navBarHiddenLinks) {
		this.navBarHiddenLinks = navBarHiddenLinks;
	}
}