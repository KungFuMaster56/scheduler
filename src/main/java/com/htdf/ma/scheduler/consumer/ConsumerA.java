package com.htdf.ma.scheduler.consumer;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.hifly.db.Db;
import com.zxe.cimiss.pasom.asm.RealTimeDataReportAsm;
import com.zxe.cimiss.pasom.asm.RealTimeHourAsmData;
import com.zxe.cimiss.pasom.lradar.RealTimeHourLRadarData;
import com.zxe.cimiss.pasom.lradar.RealTimeLRadarInfoData;
import com.zxe.cimiss.pasom.station.RealTimeAreaStation;
import com.zxe.cimiss.pasom.station.RealTimeAreaStationLast24Hour;
import com.zxe.cimiss.pasom.station.RealTimeNationStation;
import com.zxe.cimiss.pasom.station.RealTimeNationStationLast24Hour;

@Component
public class ConsumerA {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private Db db;
	
	@JmsListener(destination="ASM_REPORT")
	public void processMessageASMREPORT(String content){
		if(content.equals("ON")){
			logger.info("自动土壤水分站到报初始化接受消息，开始执行。");
			RealTimeDataReportAsm asm = new RealTimeDataReportAsm();
			asm.setDb(db);
			asm.parse(new ArrayList<String>());
			logger.info("自动土壤水分站到报初始化接受消息，执行结束。");
		}
	}
	
	@JmsListener(destination="ASM")
	public void processMessageASM(String content){
		if(content.equals("ON")){
			logger.info("自动土壤水分站解析接受消息，开始执行。");
			RealTimeHourAsmData asm = new RealTimeHourAsmData();
			asm.setDb(db);
			asm.parse(new ArrayList<String>());
			logger.info("自动土壤水分站解析接受消息，执行结束。");
		}
	}
	
	@JmsListener(destination="NAWS")
	public void processMessageNAWS(String content){
		if(content.equals("ON")){
			logger.info("国家自动站解析接受消息，开始执行。");
			try {
				RealTimeNationStation naws = new RealTimeNationStation();
				naws.setDb(db);
				naws.parse(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("国家自动站解析接受消息，执行结束。");
		}
	}
	
	@JmsListener(destination="NAWS_24")
	public void processMessageNAWS_24(String content){
		if(content.equals("ON")){
			logger.info("国家自动站补漏解析接受消息，开始执行。");
			try {
				RealTimeNationStationLast24Hour naws = new RealTimeNationStationLast24Hour();
				naws.setDb(db);
				naws.parse(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("国家自动站补漏解析接受消息，执行结束。");
		}
	}
	
	@JmsListener(destination="RAWS")
	public void processMessageRAWS(String content){
		if(content.equals("ON")){
			logger.info("区域自动站解析接受消息，开始执行。");
			try {
				RealTimeAreaStation raws = new RealTimeAreaStation();
				raws.setDb(db);
				raws.parse(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("区域自动站解析接受消息，执行结束。");
		}
	}
	
	@JmsListener(destination="RAWS_24")
	public void processMessageRAWS_24(String content){
		if(content.equals("ON")){
			logger.info("区域自动站补漏解析接受消息，开始执行。");
			try {
				RealTimeAreaStationLast24Hour raws = new RealTimeAreaStationLast24Hour();
				raws.setDb(db);
				raws.parse(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("区域自动站补漏解析接受消息，执行结束。");
		}
	}
	@JmsListener(destination="LRADAR")
	public void processMessageLRADAR(String content){
		if(content.equals("ON")){
			logger.info("探空雷达站解析接受消息，开始执行。");
			try {
				RealTimeHourLRadarData lradar = new RealTimeHourLRadarData();
				lradar.setDb(db);
				lradar.parse(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("探空雷达站解析接受消息，执行结束。");
		}
	}
	
	@JmsListener(destination="LRADAR_INFO")
	public void processMessageLRADARINFO(String content){
		if(content.equals("ON")){
			logger.info("探空雷达站INFO解析接受消息，开始执行。");
			try {
				RealTimeLRadarInfoData lradar_info = new RealTimeLRadarInfoData();
				lradar_info.setDb(db);
				lradar_info.parse(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("探空雷达站INFO解析接受消息，执行结束。");
		}
	}
}
