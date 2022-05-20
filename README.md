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
