package com.solvd.laba.carina.carinaDocsWebTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.carina.demo.gui.carinaDocsPages.desktop.OverviewPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.Configuration;

public class HeaderValidationTest implements IAbstractTest {
	public static final String CARINA_GITHUB_URL = "https://github.com/zebrunner/carina/";
	
	@Test
	@MethodOwner(owner = "lucas")
	public void testLogoTakesUserToOverviewPage() {
		OverviewPage overviewPage = new OverviewPage(getDriver());
		overviewPage.open();
		Assert.assertTrue(overviewPage.isPageOpened(), "Page not opened");
		
		Assert.assertEquals(overviewPage.clickOverviewImageAndReturnURL(), Configuration.getEnvArg("base"));
	}

	@Test
	@MethodOwner(owner = "lucas")
	public void testCarinaHeaderTextPresence() {
		OverviewPage overviewPage = new OverviewPage(getDriver());
		overviewPage.open();
		Assert.assertTrue(overviewPage.isPageOpened(), "Page not opened");
		
		String headerText = overviewPage.getCarinaHeaderText(); 
		Assert.assertEquals(headerText, "Carina");
	}
	
	@Test
	@MethodOwner(owner = "lucas")
	public void testCarinaSearchBarPlaceholderText() {
		OverviewPage overviewPage = new OverviewPage(getDriver());
		overviewPage.open();
		Assert.assertTrue(overviewPage.isPageOpened(), "Page not opened");
		
		String placeHolderText = overviewPage.getCarinaSearchBarPlaceHolderText();
		Assert.assertEquals(placeHolderText, "Search");
	}
	
	@Test
	@MethodOwner(owner = "lucas")
	public void testGitHubLinkValidation() {
		OverviewPage overviewPage = new OverviewPage(getDriver());
		overviewPage.open();
		Assert.assertTrue(overviewPage.isPageOpened(), "Page not opened");
		
		String gitHubLinkURL = overviewPage.getCarinaHeaderGitHubLink();
		Assert.assertEquals(gitHubLinkURL, CARINA_GITHUB_URL);
	}
	
	@Test
	@MethodOwner(owner = "lucas")
	public void testValidateHeaderIsSticky() {
		OverviewPage overviewPage = new OverviewPage(getDriver());
		overviewPage.open();
		Assert.assertTrue(overviewPage.isPageOpened(), "Page not opened");

		overviewPage.scrollToBottomOfPage();
		Assert.assertTrue(overviewPage.checkVisibilityOfHeader(), "Header is not visible");
	}
}