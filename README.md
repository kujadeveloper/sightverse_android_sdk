# sightverse

Android sdk<br>
https://jitpack.io/#kujadeveloper/sightverse_android_sdk


# Notes
	Your apikey security quick tip https://www.youtube.com/watch?v=X8lYNW_Or2o

# Installations
    
Step 1. Add the JitPack repository to your build file <br />
Add it in your root build.gradle at the end of repositories:<br />

<pre>
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
</pre>

Step 2. Add the dependency<br>
<pre>
	dependencies {
	        implementation 'com.github.kujadeveloper:sightverse_android_sdk:Tag'
	}
</pre>


# How to use

Call the sdk<br>
	<code>
		var sdk = Sightverse();
	</code>
	
Define application api key<br>
	<code>
		sdk.appkey = "your application api key";
	</code>
<br><br>
Call first open slide page<br>
	<pre>
        var pages: JSONObject? = sdk.firstPages();
        System.out.println(pages);
	</pre>

# Function List

Call the sdk<br>
	<code>
		var sdk = Sightverse();
	</code>

<ul>
	<li>
		<code>sdk.getCampaing("1");</code>
		Lists the campaigns defined for the application.
		The page parameter counts the paging control.
	</li>
	
	<li>
		<code>sdk.getAllUsers("1");</code>
		Lists the users defined for the application.
		The page parameter counts the paging control.
	</li>
	
	<li>
		<code>	val data = JSONObject()
			data.put("name", "Test")
			data.put("lastname", "Test")
			data.put("phone", "05555555555")
			data.put("email", "test@test.com")
			data.put("uuid", "1234567890")
			var response: JSONObject? = sdk.createUser(data);</code>
		It defines the user for the application.
	</li>
</ul>

