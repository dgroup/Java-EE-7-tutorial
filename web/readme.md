### CDI:
  Based on the principle of "loose coupling and strong typing."

### jsr330 vs jsr299

* JSR-330(Dependency Injection for Java), there was/is a lot of confusion, as JSR-330 led by Rod Johnson (SpringSource)
and Bob Lee (Google Inc.) became a part of Java EE 6.
It comes with own few annotations from the package: `javax.inject`.
The package contains the following elements: `Inject`, `Qualifier`, `Scope`, `Singleton`, `Named`(Deprecated) and `Provider`.
Its the definition of the basic dependency injection semantics.

* JSR-299 (Java Contexts and Dependency Injection), with Gavin King as lead,
uses JSR-330 as base and enhances it significantly with modularization,
cross cutting aspects (decorators, interceptors), custom scopes, or type safe injection capabilities.
 JSR-299 is layered on top of JSR-330.


Original article:

* [WHAT IS THE RELATION BETWEEN JSR-299 AND JSR-330 IN JAVA EE 6? DO WE NEED TWO DI APIS?](http://www.adam-bien.com/roller/abien/entry/what_is_the_relation_between)