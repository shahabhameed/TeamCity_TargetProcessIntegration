package com.shahabhameed;

import jetbrains.buildServer.issueTracker.AbstractIssueProviderFactory;
import jetbrains.buildServer.issueTracker.IssueProviderType;
import jetbrains.buildServer.web.openapi.PluginDescriptor;

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
public class TargetProcessIssueProviderType extends IssueProviderType {
	
    @NotNull
    private final String myConfigUrl;
    
    @NotNull
    private final String myPopupUrl;
    
    public TargetProcessIssueProviderType(@NotNull final PluginDescriptor pluginDescriptor) {
        myConfigUrl = pluginDescriptor.getPluginResourcesPath("admin/editIssueProvider.jsp");
        myPopupUrl = pluginDescriptor.getPluginResourcesPath("popup.jsp");
    }
    
    @Override
    public String getType() {
        return "TargetProcess";
    }

    @Override
    public String getDisplayName() {
        return "Target Process";
    }

    @Override
    public String getEditParametersUrl() {
        return myConfigUrl;
    }

    @Override
    public String getIssueDetailsUrl() {
        return myPopupUrl;
    }
}
