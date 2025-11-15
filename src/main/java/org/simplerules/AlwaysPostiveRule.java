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
