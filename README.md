# JAXB Runtime native - IllegalAnnotationsException

*__Please note:__ Spring Framework is used for easy native compilation, 
configuration comes almost ready-to-go. Exception is being thrown by code involving JAXB only*

Check `org.example.JAXBTest` class in `/src/test/java`

Unmarshaller runs fine on JVM.
If native profile is used, the following exception is thrown:
```
Failures (1):
  JUnit Jupiter:JAXBTest:readXml()
    MethodSource [className = 'org.example.JAXBTest', methodName = 'readXml', methodParameterTypes = '']
    => org.glassfish.jaxb.runtime.v2.runtime.IllegalAnnotationsException: 1 counts of IllegalAnnotationExceptions
org.example.HandleConn does not have a no-arg default constructor.
        this problem is related to the following location:
                at org.example.HandleConn

       org.glassfish.jaxb.runtime.v2.runtime.IllegalAnnotationsException$Builder.check(IllegalAnnotationsException.java:83)
       org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl.getTypeInfoSet(JAXBContextImpl.java:421)
       org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl.<init>(JAXBContextImpl.java:255)
       org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl$JAXBContextBuilder.build(JAXBContextImpl.java:1115)
       org.glassfish.jaxb.runtime.v2.ContextFactory.createContext(ContextFactory.java:144)
       org.glassfish.jaxb.runtime.v2.JAXBContextFactory.createContext(JAXBContextFactory.java:44)
       jakarta.xml.bind.ContextFinder.find(ContextFinder.java:368)
       jakarta.xml.bind.JAXBContext.newInstance(JAXBContext.java:605)
       jakarta.xml.bind.JAXBContext.newInstance(JAXBContext.java:546)
       org.example.JAXBTest.readXml(JAXBTest.java:15)
       [...]

```

__BUG?__

## Replication steps

* `mvn install`
* `mvn -PnativeTest test`

## JAXB repo reference
[Here](https://github.com/eclipse-ee4j/jaxb-ri/issues/1673) the related issue


## Solution

Found the solution on [GraalVM reachability metadata repo](https://github.com/oracle/graalvm-reachability-metadata) by reading their tests for JAXB.

The class being parsed requires reflection hints in `META-INF\native-image\reflect-config.json`
```json
[
    {
        "name": "org.example.HandleConn",
        "allDeclaredConstructors": true,
        "allDeclaredMethods": true,
        "allDeclaredFields": true
     }
]
```




