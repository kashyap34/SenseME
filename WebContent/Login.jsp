<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"      
      xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
      body {
        padding-top: 0px;
        padding-bottom: 140px;
        background-image: url('${pageContext.request.contextPath}/images/frontpage.png');
        backgroud-size: 1440px 800px;
        position : relative;
        background-position: 0px 0px;
        background-repeat: no-repeat;
      }

      .form-signin {
        max-width: 350px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        position: fixed;
        top: 30%;
        left: 35%;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
      
    input.button_login {
    background-image: url('${pageContext.request.contextPath}/images/fblogin.png');
    background-color: transparent; /* make the button transparent */
    background-repeat: no-repeat;  /* make the background image appear only once */
    background-position: 0px 0px;  /* equivalent to 'top left' */
    border: none;           /* assuming we don't want any borders */
    cursor: pointer;        /* make the cursor like hovering over an <a> element */
    height: 43px;           /* make this the size of your image */
    padding-left: 263px;     /* make text start to the right of the image */
    vertical-align: middle; /* align the text vertically centered */
}

</style>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/bootstrap.css" media="screen"></link>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/bootstrap-responsive.css"/>


<script type="text/javascript" src="<%= request.getContextPath()%>/CSS/bootstrap.js"></script>
<script type="text/javascript">
var isConnected = false;
window.fbAsyncInit = function() {
FB.init({
  appId      : '1424556047762433', // App ID
  //channelUrl : '//WWW.YOUR_DOMAIN.COM/channel.html', // Channel File
  status     : true, // check login status
  cookie     : true, // enable cookies to allow the server to access the session
  xfbml      : true  // parse XFBML
});

};

// Load the SDK asynchronously
(function(d){
 var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
 if (d.getElementById(id)) {return;}
 js = d.createElement('script'); js.id = id; js.async = true;
 js.src = "//connect.facebook.net/en_US/all.js";
 ref.parentNode.insertBefore(js, ref);
}(document));

// Here we run a very simple test of the Graph API after login is successful. 
// This testAPI() function is only called in those cases. 
function testAPI() {
  console.log('Welcome!  Fetching your information.... ');
  FB.api('/me', function(response) {
    console.log('Good to see you, ' + response.name + '.');
  });
}

function fb_login() {
	FB.login(function(response){
		if(response.authResponse) {
			authToken = response.authResponse.accessToken;
			console.log('Access token is: ' + authToken);
			var token = document.getElementById("userInformation:accessToken");
			token.value = authToken;
			document.getElementById("userInformation:submit").click();
		}
    });
}

function moodValueChanged(element) {
	//var ddl = document.getElementById("userInformation:moodList");
	var selectedMood = element.options[element.selectedIndex].value;
	//alert('selected mood is :' + selectedMood);
	document.getElementById("userInformation:mood").value = selectedMood;
}
</script>

<!--
Below we include the Login Button social plugin. This button uses the JavaScript SDK to
present a graphical Login button that triggers the FB.login() function when clicked.

Learn more about options for the login button plugin:
/docs/reference/plugins/login/ -->

</head>
<body onload="SetPlaceHolders();">
<f:view>
<h:form styleClass="form-signin" id="userInformation">
<div class="container">
	<h2 class="form-signin-heading">SenseMe</h2>
	<h:inputHidden id="accessToken" value="#{userInformation.accessToken}"></h:inputHidden>
	<!--<fb:login-button size="medium">Connect with Facebook</fb:login-button>-->
	<input type="button" onclick="fb_login()" class="button_login" image="/images/fblogin.png"/>
	<h:commandButton value="submitForm" id="submit" action="#{userInformation.fetchUserDetails }" style="visibility:hidden">
	</h:commandButton>
	<br/>
	<br/>

	<h:inputHidden id="mood" value="#{moodProcessor.mood}"></h:inputHidden>
	<select onchange="moodValueChanged(this)" id="moodList">
		<option value="Select your mood">Select your mood</option>
		<option value="happy">happy</option>
		<option value="angry">angry</option>
		<option value="excited">excited</option>
		<option value="relaxing">relaxing</option>
		<option value="sad">sad</option>
	</select>
</div>
</h:form>
</f:view>
</body>
</html>