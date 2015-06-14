package com.shahabhameed;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jetbrains.buildServer.issueTracker.AbstractIssueProvider;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.issueTracker.SIssueProvider;

import org.jetbrains.annotations.NotNull;

/**
 * An issue provider for Target Process which extracts the issues from the string based on a regular expression. 
 * 
 * @author Muhammad Shahab Hameed
 * @version 1.0
 * @since 1 June 2015
 * 
 */
public class TargetProcessIssueProvider extends AbstractIssueProvider implements SIssueProvider{

    public TargetProcessIssueProvider(@NotNull IssueFetcher fetcher) {
        super("targetprocess", fetcher);
    }

    @NotNull
    @Override
    protected Pattern compilePattern(@NotNull final Map<String, String> properties) {
    	Pattern result = super.compilePattern(properties);
        if (myFetcher instanceof TargetProcessIssueFetcher) {
            TargetProcessIssueFetcher fetcher = (TargetProcessIssueFetcher)myFetcher;
            fetcher.setPattern(result);
        }
        return result;
    }
    
    @Override
    protected String extractId(String match) {
    	Matcher matcher = myPattern.matcher(match);
        matcher.find();
        return matcher.group(1);
    }
}
