package com.shahabhameed;

import jetbrains.buildServer.issueTracker.AbstractIssueProviderFactory;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.issueTracker.IssueProvider;
import jetbrains.buildServer.issueTracker.IssueProviderFactory;

import org.jetbrains.annotations.NotNull;

/**
 * This class implements the {@link AbstractIssueProviderFactory} and overrides values to be displayed on the UI.
 * 
 * @author Muhammad Shahab Hameed
 * @version 1.0
 * @since 1 June 2015
 * 
 */
public class TargetProcessIssueProviderFactory extends AbstractIssueProviderFactory implements IssueProviderFactory{
	
	@SuppressWarnings("deprecation")
	public TargetProcessIssueProviderFactory(@NotNull IssueFetcher fetcher) {
        super(fetcher, "targetprocess");
    }

	@Override
    public String getDisplayName() {
        return "TargetProcess";
    }
	
    @NotNull
    public IssueProvider createProvider() {
        return new TargetProcessIssueProvider(myFetcher);
    }
}
