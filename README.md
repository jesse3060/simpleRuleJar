# Simple Rules Jar

This project serves as an example project for defining new `ReportingRules` that can be uploaded in the `Reporting-Proxy` addon for [Zaproxy](https://www.zaproxy.org/)

## Description

The `Reporting-Proxy` addon in Zaproxy allows developers to configure ZAP with a set of rules, so that they can receive live notifications 
when a rule matches the observed traffic. These rules can be uploaded in a jar file via the Zaproxy gui. See (LINK TO CHAPTER) on how to do this.

This project can be forked to easily get started on defining your own `ReportingRules`. Further below, there are more examples and documentation if you want to create your own project from scratch. 

## Getting Started

### Dependencies
To get started defining new rules, you need two dependencies in your project

* Zaproxy (to use the [HttpMessage type](https://javadoc.io/doc/org.zaproxy/zap/2.9.0/org/parosproxy/paros/network/HttpMessage.html))
* `Reporting-Proxy` api, defined in a jar (see sources).

Concretely, you need to add the follow two dependencies to the `build.gradle.kts` file:
```gradle
dependencies {
    implementation(files("libs/reportingproxy-api.jar"))
    implementation("org.zaproxy:zap:2.15.0")
}
```

### Defining new Rules

A valid `ReportingRule` always needs to implement the `ReportingRule` interface which consits of one `handleMessage` method. This method returns a `ReportingRuleResult` instance, which can either be a `ReportingRuleSucces` or `ReportingRuleFailure` 

*Example:*
```Java
package org.simplerules;
import org.zaproxy.addon.reportingproxy.api.ReportingRule;
import org.zaproxy.addon.reportingproxy.api.ReportingRuleResult;
import org.zaproxy.addon.reportingproxy.api.ReportingRuleSuccess;

public class AlwaysPostiveRule implements ReportingRule {

    @Override
    public ReportingRuleResult handleHttpMessage(org.parosproxy.paros.network.HttpMessage msg) {
        return new ReportingRuleSuccess(this);
    }
}
```

A `ReportingRuleSucces` only holds a reference to the rule that triggered it. A `ReportingRuleFailure` however can contain more info about the rule that was triggered. It always contains the rule that was triggered, 
the `HttpMessage` that triggered it, a description message and a list of all relevant `HttpMessages`.
*Example:* 
```Java
package org.simplerules;

import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.addon.reportingproxy.api.ReportingRule;
import org.zaproxy.addon.reportingproxy.api.ReportingRuleFailure;
import org.zaproxy.addon.reportingproxy.api.ReportingRuleResult;

import java.util.List;

public class AlwaysNegativeRule implements ReportingRule {

    @Override
    public ReportingRuleResult handleHttpMessage(HttpMessage msg) {
        return new ReportingRuleFailure(this, msg, List.of(msg), "I always trigger!");
    }
}
```
You can implement a rule however you like, however **the constructor is not allowed to have parameters.**

To view the full documentation of the `ReportingRule`, `ReportingRuleSuccess` and `ReportingRuleFailure`, please have a look at the [Reporting-Proxy Repository](https://gitlab.kuleuven.be/distrinet/education/design-of-software-systems/2025-2026/Group-04/zap-extensions/-/tree/reporting-proxy/main/addOns/reportingproxy/src/main/java/org/zaproxy/addon/reportingproxy/api?ref_type=heads)

### Building the rule jar

After you have implemented new rules, build your jar using Gradle

```bash
./gradlew build
```

### Uploading your jar

TODO SHOW PICTURES ON HOW TO UPLOAD JAR

## Help

If you encounter any issues or if you have any feedback, please contact us.

## Authors
[Adriana D'Hooghe](adriana.dhooghe@student.kuleuven.be)
[Rowan Helewaut](rowan.helewaut@student.kuleuven.be)
[Jesse Op 't Eynde](jesse.opteynde@student.kuleuven.be)
[Marie Schrevens](marie.schrevens@student.kuleuven.be)
[Mats Vanhamel](mats.vanhamel@student.kuleuven.be)
[Wout Vanhecke](wout.vanhecke@student.kuleuven.be)



## Version History

* 1.0
    * Initial Release
