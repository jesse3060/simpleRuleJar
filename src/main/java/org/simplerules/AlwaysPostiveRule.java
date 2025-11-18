package org.simplerules;
import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.addon.reportingproxy.api.ReportingRule;
import org.zaproxy.addon.reportingproxy.api.ReportingRuleResult;
import org.zaproxy.addon.reportingproxy.api.ReportingRuleSuccess;

public class AlwaysPostiveRule implements ReportingRule {

    @Override
    public ReportingRuleResult handleHttpMessage(org.parosproxy.paros.network.HttpMessage msg, boolean isSendRequest) {
        return new ReportingRuleSuccess(this);
    }

    @Override
    public String getRuleDisplayName() {
        return "Always Positive";
    }
}
