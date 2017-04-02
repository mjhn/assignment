# Zillow assignment

## Zillow GetSearchResults integration.

### Outline
Submit street address details to Zillow GetSearchResults. Present output to stdout for consumption by caller.

Supported input formats

#### JSON input format
```javascript
/**
*    minimum input requirements.
*    All are mandatory except zip which is optional.
*/
{
	"streetNumber": "2114",
	"streetName": "Bigelow Ave",
	"city": "Seattle",
	"state":"WA",
	"zip":"98109"
}	
```
#### XML input format	
```xml
<!--
	minimum input requirements.
	All are mandatory except zip which is optional.
-->
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<Zillowquery>
  <streetNumber>5678</streetNumber>
  <streetName>this street</streetName>
  <city>Wako</city>
  <state>TX</state>
  <zip>12345</zip>
</Zillowquery>
```

#### Output
Output format is always xml. Remaining integrated and consistent with Zillows output format.
```xml
<?xml version="1.0" encoding="utf-8"?>
<SearchResults:searchresults xsi:schemaLocation="http://www.zillow.com/static/xsd/SearchResults.xsd http://www.zillowstatic.com/vstatic/0378a4e/static/xsd/SearchResults.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:SearchResults="http://www.zillow.com/static/xsd/SearchResults.xsd">
...
<message>
	<text>Error: invalid or missing ZWSID parameter</text>
	<code>2</code>
</message>
...
```	
The result of any invocation is achieved by evaluating
'SearchResults/Message/code' representing the result of the invocation.
'SearchResults/Message/text' providing detail of the invocation.

#### Code value semantics	
0   == Sucess
> 0 == A Zillow error has occured
< 0 == An error within the application has occured.

#### Zillow Documentation
https://www.zillow.com/howto/api/GetSearchResults.htm

### Run sample application
Ensure at least java 1.6 is available on your system
From a command line console cd to the *build* directory.
Execute - *run.bat -f ../input.json -t json* or *run.bat -f ../input.xml -t xml*



 
