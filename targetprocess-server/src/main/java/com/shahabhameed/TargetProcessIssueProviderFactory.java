package com.shahabhameed;

import jetbrains.buildServer.issueTracker.AbstractIssueProviderFactory;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.issueTracker.IssueProvider;
import jetbrains.buildServer.issueTracker.IssueProviderFactory;
import jetbrains.buildServer.issueTracker.IssueProviderType;

import org.jetbrains.annotations.NotNull;

/**
 * This class implements the {@link AbstractIssueProviderFactory} and overrides values to be displayed on the UI.
 * 
 * @author Muhammad Shahab Hameed
 * @version 1.0
 * @since 1 June 2015
 * @updated 8 January 2016
 * 
 */
public class TargetProcessIssueProviderFactory extends AbstractIssueProviderFactory implements IssueProviderFactory{
	
	public TargetProcessIssueProviderFactory(@NotNull final IssueProviderType type, @NotNull IssueFetcher fetcher) {
	    super(type, fetcher);
    }

    @NotNull
    public IssueProvider createProvider() {
        return new TargetProcessIssueProvider(myType, myFetcher);
    }
}
