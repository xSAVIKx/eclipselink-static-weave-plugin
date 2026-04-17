# Introduction
Plugin which performs Eclipselink static weaving. Use the weave goal to execute. 

Heavily inspired by https://code.google.com/p/eclipselink-staticweave-maven-plugin. 
This is an updated and enhanced version to be compatible with Java 8 and Maven 3.x, and it is validated against EclipseLink 2.7.16, 3.0.4, 4.0.9, and 5.0.0.

Internally the StaticWeaveProcessor is used, like described in the EclipseLink Wiki https://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Advanced_JPA_Development/Performance/Weaving/Static_Weaving#Use_the_Command_Line. 

Do not forget to add EclipseLink as dependency, otherwise the EclipseLink StaticWeaveProcessor is not found.

# Compatibility

The plugin is compiled against EclipseLink 2.7.16 and tested against these versions:

* 2.7.16
* 3.0.4
* 4.0.9
* 5.0.0

Use a plugin dependency that matches the EclipseLink major version used by your application.

# Common Usage
```xml
 <build>
   	...
   <plugins>
 			 <plugin>
 			 	<groupId>io.github.xsavikx</groupId>
				<artifactId>staticweave-maven-plugin</artifactId>
				<version>1.1.1</version>
 				<executions>
 					<execution>
 						<phase>process-classes</phase>
 						<goals>
 							<goal>weave</goal>
 						</goals>
 						<configuration>
 							<persistenceXMLLocation>META-INF/persistence.xml</persistenceXMLLocation>
 							<logLevel>FINE</logLevel>
 						</configuration>
 					</execution>
 				</executions>
 				<dependencies>
 					<dependency>
 						<groupId>org.eclipse.persistence</groupId>
						<artifactId>eclipselink</artifactId>
						<version>${eclipselink.version}</version>
 					</dependency>
 				</dependencies>
 			</plugin>
   		
   		...
   	</plugins>
   	...
 </build>
```
# Plugin Options

## persistenceXMLLocation
Give here the location of your persistence.xml file. This property is optional. If not set the default location META-INF/persistence.xml is used.

## source
The location of the JPA classes. This property is optional, default value is ${project.build.outputDirectory}.

## target
The location for the weaved classes. This property is optional, default value is ${project.build.outputDirectory}.

## logLevel
The Logging level of the used EclipseLink StaticWeave class. This property is optional, default value is FINE to get informed which JPA classes were woven.
	 
Possible values:
	 
* OFF
* SEVERE
* WARNING
* INFO
* CONFIG
* FINE
* FINER
* FINEST
* ALL

The EclipseLink logging information is always given in Maven INFO loglevel.

# Running Tests

Use the Maven wrapper from the repository root:

* `.\mvnw.cmd clean verify` runs the full build and the integration tests for the default EclipseLink baseline.
* `.\mvnw.cmd clean verify "-Declipselink.version=2.7.16"` validates compilation and weaving against EclipseLink 2.7.16.
* `.\mvnw.cmd clean verify "-Declipselink.version=3.0.4"` validates compilation and weaving against EclipseLink 3.0.4.
* `.\mvnw.cmd clean verify "-Declipselink.version=4.0.9"` validates compilation and weaving against EclipseLink 4.0.9.
* `.\mvnw.cmd clean verify "-Declipselink.version=5.0.0"` validates compilation and weaving against EclipseLink 5.0.0.

Happy weaving!
