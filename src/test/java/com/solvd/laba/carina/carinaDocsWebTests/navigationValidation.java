package com.solvd.laba.carina.carinaDocsWebTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.carina.demo.gui.carinaDocsPages.desktop.OverviewPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class navigationValidation implements IAbstractTest {

	@Test
	@MethodOwner(owner = "lucas")
	public void testNavBarTextAndLinkPresenceValidation() {
		OverviewPage overviewPage = new OverviewPage(getDriver()); 
		overviewPage.open();
		Assert.assertTrue(overviewPage.isPageOpened(), "Page not opened");
		
		Assert.assertEquals(overviewPage.getCarinaNavBarHeadingText(), "Carina");
		Assert.assertTrue(overviewPage.checkPresenceOfNavBarLinks(), "No Navigation bar links found");
		Assert.assertTrue(overviewPage.checkOverviewLinkHighlighted(), "Overview link not highlighted");
	}
	
	@Test
	@MethodOwner(owner = "lucas")
	public void testNavBarHiddenLinks() {
		OverviewPage overviewPage = new OverviewPage(getDriver()); 
		overviewPage.open();
		Assert.assertTrue(overviewPage.isPageOpened(), "Page not opened");
		
		Assert.assertFalse(overviewPage.checkVisibilityOfHiddenNavBarLinks());
		overviewPage.clickParentNavBarLinks();
		Assert.assertTrue(overviewPage.checkVisibilityOfHiddenNavBarLinks(), "Hidden links not displayed");
	}
	
	@Test
	@MethodOwner(owner = "lucas")
	public void testValidateNavigationBarLinkColors() {
		OverviewPage overviewPage = new OverviewPage(getDriver());
		overviewPage.open();
		Assert.assertTrue(overviewPage.isPageOpened(), "Page not opened");

		
		Assert.assertTrue(overviewPage.clickEveryNavLinkAndValidateColors(), "Some Links are not getting highlighted");
	}
}