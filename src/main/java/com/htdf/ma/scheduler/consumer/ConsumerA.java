package com.htdf.ma.scheduler.consumer;

import javax.annotation.Resource;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.hifly.db.Db;
import com.zxe.cimiss.pasom.asm.RealTimeDataReportAsm;

@Component
public class ConsumerA {
	
	@Resource
	private Db db;
	
	@JmsListener(destination="msg.topic")
	public void processMessage(String content){
		if(content.equals("A")){
			System.out.println(content+" :Receiving a message!");
			RealTimeDataReportAsm asm = new RealTimeDataReportAsm();
			asm.setDb(db);
			asm.parse(null);
		}
	}
}
