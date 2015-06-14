<%@ page import="com.shahabhameed.TargetProcessIssueFetcher" %>
<%@ include file="/include.jsp"%>

<jsp:useBean id="issue" scope="request" type="jetbrains.buildServer.issueTracker.IssueEx"/>
<c:set var="issueData" value="${issue.issueDataOrNull}"/>
<c:set var="fields" value="${issueData.allFields}"/>

<c:set var="fixVersionField"><%=TargetProcessIssueFetcher.STATE_FIELD%></c:set>
<c:set var="priorityValueField"><%=TargetProcessIssueFetcher.PRIORITY_FIELD%></c:set>
<c:set var="fixVersion"><%=TargetProcessIssueFetcher.SEVERITY_FIELD%></c:set>

<c:set var="priorityValue"><%=TargetProcessIssueFetcher.STATE_NUMERIC_FIELD%></c:set>

<bs:issueDetailsPopup issue="${issue}"
                       popupClass="yt"
                       priorityClass="p${fields[priorityValue]}">
  <jsp:attribute name="otherFields">
  </jsp:attribute>
</bs:issueDetailsPopup>
