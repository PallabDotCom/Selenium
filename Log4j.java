//Log4j is reliable, fast and flexiable logging framework(APIs) written in Java which distributed under the apache software license.
//You can use this utility for selenium, appium, rest API. It can be integrated to any Java based framework.

//Download apache log4j jar (bin.zip). after unzip import Log4j core & log4j api in java.
//Have tio create log object for function getlogger and have to pass class as argument.

Example of syntax : 
import alpha;
import org.apache.logging.log4j.*;

public class demo 
{
//We should use this line in every class of framework to get log.
private static Logger log=LogManager.getlogger(Demo.class.getName());

public static void main(String[] args)
{
log.debug ("I have clicked on button");
log.info ("button displayed");
log.error("button not displayed");
log.fatal("button misssing");
}
}


// When to Use log. Error, debug and info methods in Selenium test cases ?

Use log. Error() to log when elements are not displayed in the page or if any validations fail
Use Log. Debug() : When each Selenium action is performed like click, SendKeys, getText()
Use log.info() : When operation is successfully completed ex: After loading page, or after any successful validations.It’s just counterpart to log. Error()

//Default configuation is just to print errors.

//How to define configuation file (log4j XML file)? - Check apache website 
https://logging.apache.org/log4j/2.x/manual/configuration.html

// Appenders & loggers is basic 2 tag.
//Where and how to log is defined under Appenders Tag
//What to log is defined under loggers root level. 
//Create a xml file in separate folder (lets say resources) of same project. Now project-> build path -> configure build path -> Source folder->add folder-> resources (add and ok)
// Log only when there is error for package A ? - <Logger name="packageA.classname" level="error" additivity="false">
//Log the entire message for package B - For all the contents we should mention "trace" instead "error" like <Root level="trace"> or <Logger name="packageB.classname" level="trace" additivity="false">


//Sample Log4j XML File - 

<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">

 <Properties>
   <Property name="basePath">./logs</Property> 
	//This is the location where you want to place your notepad file name prints.log for printing output.
 </Properties>
 
  <Appenders>
     <RollingFile name="File" fileName="${basePath}/prints.log" filePattern="${basePath}/prints-%d{yyyy-MM-dd}.log"> 
	//fileName is the path where the log we will be saving.
	<PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
	<SizeBasedTriggeringPolicy size="500" />
     </RollingFile>

    <Console name="Console" target="SYSTEM_OUT">
      <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>
  </Appenders>

  <Loggers>
	<Logger name="packageB.classname" level="trace" additivity="false"> 
		//additivity controls duplication of output message
	<AppenderRef ref="Console"/>
		//packageB.classname will be printed in console
	</Logger>

	<Root level="trace">
           <AppenderRef ref="File"/> 
		//based on this AppenderRef, log4j will print the output either in file or in console.
		//In this case packageA.classname ( if u have 2 package A & B) will be printed in file.
        </Root>
  </Loggers>
</Configuration>




