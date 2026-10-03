# Getting Started

## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.1/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.1/maven-plugin/build-image.html)

## Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## Step-by-step Documentation
This repository was first created in GitHub with the purpose of showcasing practical coding skills
to any recruiters that come across my resume.
It focuses on the backend part of the application, since my expertise has been almost exclusively working with backend microservices.

### Spring
To kickstart the process, I used [Spring Initializr](https://start.spring.io) with the following configurations:
* Project: Maven (as opposed to Gradle XXX)
* Language: Java (as opposed to Kotlin or Groovy)
* Spring Boot: 4.1.1
* Project Metadata
  * Group: com.github.journal
  * Artifact: customer
  * Packaging: Jar (as opposed to War)
  * Configuration: YAML (as opposed to Properties)
  * Java: 17
  
These are the reasons why those choices were made:

#### Project
Maven is more structured (as the definition file pom.xml is XML), so it's harder to write incorrect files.
It also is quite simple as it essentially comes down to one document pom.xml,
meanwhile Gradle must be paired with a coding language (Kotlin or Groovy), which adds the effort of checking for bugs in the code.

Gradle is recommended when:
* Customization is needed/important (e.g. code is very old, requiring more customization to compile it)
* Code is very large, enough so that the better performance of Gradle matters
* You're forced to use it by some technology (e.g. Android or writing plugins for IntelliJ IDEA)

#### Language
I'm simply way more familiarized with Java than with the other options.
Besides, Maven was designed and is primarily used for Java projects,
kind of the same way Python has PIP and Node has NPM, to use external libraries.

#### Spring Boot
The version 4.1.1 was the latest non-SNAPSHOT option available.
While artifacts are immutable (2 artifacts with the same version must be the same),
SNAPSHOTs are mutable by definition and every new build can change the artifact published under the -SNAPSHOT version.
By the way, SNAPSHOT artifacts are updated DAILY by default,
which is why they're not accepted by the Maven Central, and why it's best to avoid them.

#### Project Metadata

##### — Group/Artifact
Personal choice/taste, and it easily identifies this MS amongst my other GitHub repositories.

##### — Packaging
JAR files allow us to package multiple files in order to use it as a library, plugin, or any kind of application.
On the other hand, WAR files are used only for web applications, which is not what we're looking for right now.

We can also run a JAR from the command line if we build it as an executable JAR without using additional software.
Or, we can use it as a library. In contrast, we need a server to execute a WAR.
This makes it simpler to handle JARs as opposed to WARs.

##### — Configuration
These are some of the advantages of using YAML instead of the properties file in Spring Boot:
* More human-friendly (Better readability)
* Reduces repetition
  * Example: If we have 2 properties (abc.111 & abc.222), we'll have to write both completely;
  * But with YAML, we write abc only once
* From version 1.2, YAML is a superset of JSON
* YAML allows the possibility to include several profiles in the same file
  * Although, properties files also have this feature with Spring Boot 2.4.0
* We can assign environment values to variables and even set a default value just in case
  * This is specially useful for deployments
  * With properties files, we can only assign a single value, defined inside the file itself

The only major annoying thing about YAML is that indentation is super important,
and it can be the root of one of those dumb bugs that sometimes takes forever for us developers to find.
However, once you get used to it, it rarely happens.

##### — Java
* Java 8 and 11 are used in older systems, since they were very reliable at the time, but they have limitations
(Note: v11 is basically v8 with some extra features).

* Java 17 is currently the most-used Long-Term Support (LTS) version in production environments,
and many older codebases are upgrading from v8/11 to v17, so it's worth learning it.

* However, Java 21 is rapidly gaining adoption and this is probably what you should learn if you’re looking for a serious Java position.
Very few companies have made the effort to keep up with the utmost recent versions of Java (like startups).
Note that Java 25 has considerably more features than v21, so it's worth taking a look at it after getting used to v21.

In the end, I chose Java 17, because not only it's currently the most used one,
but it's also the latest one I learned at my last job.

### Dependencies
I only add dependencies when I need them.
For example, the first one I had to add was the Spring Web MVC one, so I could create the customer controller.
I noticed it was missing, because IntelliJ couldn't find the @RestController annotation.
* Basically, I'll add dependencies only when they're actually necessary.

This habit also gave me the idea to add a comment on top of each dependency, describing the reason it was added.
It's particularly useful when we have to upgrade their versions and two dependencies are incompatible.
When we know why a certain library was added, we can try to find alternate solutions that don't require that specific library.

Note that when adding new dependencies on your pom.xml, the computer will complain it doesn't exist, until you sync the changes.
Do it, and it will get the latest version of whatever you added, unless you specified another version.

Also, when IntelliJ complains about multiple dependencies missing, I don't add all of them straight away.
Instead, I add them one by one, to have a better idea of what each one is capable of.
For example, that's how I found that, for the models of a swagger to be generated,
I just needed the jackson annotation libraries the IDE asks for (the others are only necessary for the generated models to be functional).
This can be useful if you simply want to check on something really quick.

#### Q&A

##### — Why use @RestController over @Controller?
TBD

##### — Why use openapi-generator-maven-plugin over swagger-codegen-maven-plugin?
The older `io.swagger:swagger-codegen-maven-plugin` is another option, particularly for Swagger 2.0 specs,
but `org.openapitools:openapi-generator-maven-plugin` is generally the recommended choice for new projects.

Since we're using the latest version of TMF629 (v5.0.1), we'll use the OpenAPI Generator.

##### — Why set OpenAPI Generator plugin's generateSupportingFiles to true?
You can set that to false if you're only generating models,
but if you also want to generate APIs, there will be classes inside those files that you won't find in any library
(because they're generated by the plugin).
For example, the ApiUtil class.

Note that it is true by default, so in this case, you would simply need to remove the tag.

##### — Why add @Controller or @Service to classes?
Those annotations make sure beans are created for those classes.
Even if you didn't want to use those annotations for some reason, 
you would still need to add a @Bean annotation, at the very least, 
for the other classes to be able to call them and use their functions.

#### Tips
* When adding dependencies on your pom.xml, you don't need to sync the changes if you're going to `clean install`.
  * The command does it for you

#### Troubleshooting
java.lang.RuntimeException: Issues with the OpenAPI input. Possible causes: invalid/missing spec, malformed JSON/YAML files, etc.
* Don't panic, simply look at the exception more closely
* Chances are, inside your pom.xml, you didn't specify the correct path to the swagger for the plugin

'dependencies.dependency.version' for org.openapitools:jackson-databind-nullable:jar is missing.
* Again, don't panic, just read the exception more carefully
* Chances are for Maven to find the dependency you want, you need to specify its version

Unit test classes can't recognize a package that exists (its dependency is in the pom.xml)
* Check if the dependency has its scope set to 'test'
* If so, that's your issue, remove it
  * Example > java: package org.springframework.boot.test.context does not exist
  * I just needed to remove the scope flag from my spring-boot-starter-test dependency

org.springframework.boot:spring-boot-maven-plugin:4.1.1:repackage failed: Unable to find a single main class from the following candidates
* This means there are 2+ classes annotated with @SpringBootApplication
* All you have to do is go to spring-boot-maven-plugin and add a configuration, stating which one is your true main class
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <mainClass>com.github.journal.customer.CustomerApplication</mainClass>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>repackage</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
