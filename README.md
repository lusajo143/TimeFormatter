# TimeFormatter (Android Library)
Formats past time by comparing with the current time.

### Preparation

- Add the following in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

- Add the dependency
```gradle
dependencies {
	        implementation 'com.github.lusajo143:TimeFormatter:1.0.3'
	}
```

### Usage
```java
try {
      Date date = new Date();
      
      TimeFormatter formatter = new TimeFormatter(date.getTime());
      String time = formatter.format();
      
      // Use time as you wish
      Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
      
} catch (JSONException e) {
    e.printStackTrace();
}
```
----
Fill free to contribute :grin:
