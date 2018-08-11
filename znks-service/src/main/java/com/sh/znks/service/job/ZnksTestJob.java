package com.sh.znks.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wuminggu on 2018/6/5.
 */
public class ZnksTestJob {
    private final static Logger log = LoggerFactory.getLogger(ZnksTestJob.class);

    public void execute(){
        log.error("L13_test");
    }
}
