package com.example.shared.utilities.auditlog;

import com.example.shared.models.AuditEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditLogger {
    private static final Logger logger = LogManager.getLogger(AuditLogger.class);

    public void logEvent(AuditEvent event) {
        try {
            logger.info(event.toString());
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }
}
