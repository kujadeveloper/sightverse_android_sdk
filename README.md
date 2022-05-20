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
		<code>sdk.getCampaing("1");</code><br>
		Lists the campaigns defined for the application.
		The page parameter counts the paging control.
	</li>
	<li>
		<code>sdk.getAllUsers("1");</code><br>
		Lists the users defined for the application.
		The page parameter counts the paging control.
	</li>
	<li>
		<pre>	val data = JSONObject()
			data.put("name", "Test")
			data.put("lastname", "Test")
			data.put("phone", "05555555555")
			data.put("email", "test@test.com")
			data.put("uuid", "1234567890")
			var response: JSONObject? = sdk.createUser(data);</pre><br>
		It defines the user for the application.
	</li>
	<li>
		<pre>
		 val data_delete = JSONObject()
		data_delete.put("id", "1")
		var delete: JSONObject? = sdk.deleteUser(data_delete);
		System.out.println(delete);
		</pre>
		Deletes the user defined in the system
	</li>
	<li>
		<pre>
		var apiuser: JSONObject? = sdk.getUser("1234567890");
       		System.out.println(apiuser);
		</pre>
		uuid lists the called user
	</li>
	<li>
		<pre>
		var apiuser: JSONObject? = sdk.getUser("1234567890");
       		System.out.println(apiuser);
		</pre>
		uuid lists the called user
	</li>
	<li>
		<pre>
			var receipts: JSONObject? = sdk.receiptAppList("1");
        		System.out.println(receipts);
		</pre>
		Lists all receipts sent to the application
	</li>
	<li>
		<pre>
			 var user_receipts: JSONObject? = sdk.userAllReceipt("1");
       			 System.out.println(user_receipts);
		</pre>
		Lists all receipts sent by the user
	</li>
	<li>
		<pre>
			 val receipt_delete = JSONObject()
			receipt_delete.put("id", "1")
			var delete_receipt: JSONObject? = sdk.deleteReceipt(receipt_delete);
			System.out.println(delete_receipt);
		</pre>
		Deletes a sent receipt
	</li>
	<li>
		<pre>
			 var userme: JSONObject? = sdk.userMe();
        		System.out.println(userme);
		</pre>
		Api key returns defined user information
	</li>
	<li>
		<pre>
			var earning: JSONObject? = sdk.userEarning();
        		System.out.println(earning);
		</pre>
		Returns user gain with api key defined
	</li>
	<li>
		<pre>
			var earning: JSONObject? = sdk.userEarning();
        		System.out.println(earning);
		</pre>
		Returns user gain with api key defined
	</li>
	<li>
		<pre>
			var earning: JSONObject? = sdk.sendScreenShot(imageBitmap);
		    System.out.println(earning);
		</pre>
		Sends the given bitmap voucher for the api key defined user
	</li>
</ul>

