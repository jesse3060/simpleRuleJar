package org.simplerules;

import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.addon.reportingproxy.api.ReportingRule;
import org.zaproxy.addon.reportingproxy.api.ReportingRuleFailure;
import org.zaproxy.addon.reportingproxy.api.ReportingRuleResult;


import java.util.List;

public class AlwaysNegativeRule implements ReportingRule {

    @Override
    public ReportingRuleResult handleHttpMessage(HttpMessage msg, boolean isSendRequest) {
        return new ReportingRuleFailure(this, msg, List.of(msg), "I always trigger!");
    }

    @Override
    public String getRuleDisplayName() {
        return "Always Negative";
    }
}
