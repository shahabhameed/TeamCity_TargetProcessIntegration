package com.shahabhameed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jetbrains.buildServer.issueTracker.AbstractIssueFetcher;
import jetbrains.buildServer.issueTracker.IssueData;
import jetbrains.buildServer.util.cache.EhCacheUtil;

import org.apache.commons.httpclient.Credentials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellij.util.containers.hash.HashMap;

/**
 * This class fetches issues from a Target Process issue tracker server
 * 
 * @author Muhammad Shahab Hameed
 * @version 1.0
 * @since 1 June 2015
 * 
 */
public class TargetProcessIssueFetcher extends AbstractIssueFetcher {
	
	 public static final String SUMMARY_FIELD = "Summary";
	 public static final String STATE_FIELD = "State";
	 public static final String TYPE_FIELD = "Type";
	 public static final String PRIORITY_FIELD = "Priority";
	 public static final String SEVERITY_FIELD = "Severity";

	 private Pattern myPattern;

  public TargetProcessIssueFetcher(@NotNull EhCacheUtil cacheUtil) {
	    super(cacheUtil);
  }
  
  public void setPattern(final Pattern _myPattern) {
    myPattern = _myPattern;
  }

  public class TargetProcessFetchFunction implements FetchFunction {
    private String host;
    private String issueId;
    private Credentials credentials;

    public TargetProcessFetchFunction(final String _host, final String _issueId, final Credentials _credentials) {
      host = _host;
      issueId = _issueId;
      credentials = _credentials;
    }

    @NotNull
    public IssueData fetch() {
      String url = getUrl(host, issueId) + "?format=json";
      
      try {
    	  
    	  InputStream json = fetchHttpFile(url, credentials);
    	  
    	  IssueData result = null;
    	  
    	  if (json != null) {
    		  result = parseIssue(issueId, url, json);
    	  }
          
    	  if (result == null) {
              throw new RuntimeException("Failed to fetch issue from \"" + host + "\"");
          }
        
        return result;
        
      } catch (IOException e) {
    	  throw new RuntimeException("Failed to fetch issue from \"" + host + "\"");  
      }
    }

    private IssueData parseIssue(String issueId, String url, final InputStream json) {
    	
    	IssueData issueData = null;
    	BufferedReader reader = new BufferedReader(new InputStreamReader(json));
        
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject= (JsonObject) jsonParser.parse(reader);
      
        String summary =  jsonObject.get("Name").getAsString();
        
        JsonObject originalState = (JsonObject) jsonObject.get("EntityState");
        String state = originalState.get("Name").getAsString();
        
        JsonObject severityState = (JsonObject) jsonObject.get("Severity");
        String severity = severityState.get("Name").getAsString();
        
        JsonObject priorityState = (JsonObject) jsonObject.get("Priority");
        String priority = priorityState.get("Name").getAsString();
        
        JsonObject typeState = (JsonObject) jsonObject.get("EntityType");
        String type = typeState.get("Name").getAsString();
        
        boolean resolved=false;
        boolean isfeatureRequest=false;

        try{
	        if(state != null) {
	        	
	            if(state.equalsIgnoreCase("Closed") ){
	            	resolved = true;
	            }
	        }
	        
	        if(type != null) {
	        	
	            if(type.equalsIgnoreCase("Feature") ){
	            	isfeatureRequest = true;
	            }
	        }
	        
        }catch (Exception e){
        	// no need to catch exception
        }
        
        Map<String, String> map = new HashMap<String, String>();
        map.put(SUMMARY_FIELD, summary);
        map.put(STATE_FIELD, state);
        map.put(TYPE_FIELD, type);
        map.put(PRIORITY_FIELD, priority);
        map.put(SEVERITY_FIELD, severity);
        
        issueData = new IssueData(issueId, map, resolved, isfeatureRequest, url);
        return issueData;
  }

  }

  @NotNull
  public IssueData getIssue(@NotNull final String _host, @NotNull final String _issueId, @Nullable final org.apache.commons.httpclient.Credentials _credentials)
          throws Exception {
    String url = getUrl(_host, _issueId);
    return getFromCacheOrFetch(url, new TargetProcessFetchFunction(_host, _issueId, _credentials));
  }

  public String getUrl(@NotNull final String _host, @NotNull final String _id) {
    String realId = _id;
    Matcher matcher = myPattern.matcher(_id);
    if (matcher.find()) {
      realId = matcher.group(1);
    }
    StringBuilder url = new StringBuilder();
    url.append(_host);
    if (!_host.endsWith("/")) {
      url.append("/");
    }
    
    url.append("api/v1/Bugs/");
    url.append(realId);
    return url.toString();
  }

}