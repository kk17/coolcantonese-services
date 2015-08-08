package net.kk17.coolcantonese.record;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.kk17.common.QueryOrder;
import net.kk17.coolcantonese.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RecordTest {
	@Autowired
	Datastore datastore;
	@Autowired
	RecordService recordService;
	
	@Test
	public void testAddRecord(){
		datastore.delete(datastore.createQuery(Record.class));
		for (long i = 1; i <= 10; i++) {
			Record record = new Record();
			record.setUserId(Long.toString(i));
			record.setContent(Long.toString(i));
			record.setTime(new Date());
			
			Record reply = new Record();
			reply.setUserId("translator");
			reply.setContent("reply"+Long.toString(i));
			reply.setTime(new Date());
			record.setReply(reply);
			recordService.addRecord(record);
		}
	}
	
	@Test
	public void testGetRecords(){
		List<Record> records = recordService.getRecords(0, 5, QueryOrder.ASC);
		for (Record record : records) {
			System.out.println(record);
		}
	}
	
	@Test
	public void testGetUserRecords(){
		List<Record> records;
//		List<Record> records = recordService.getUserRecords("1",0, 5, QueryOrder.ASC);
//		for (Record record : records) {
//			System.out.println(record);
//		}
		records = recordService.getUserRecords("translator",0, 5, QueryOrder.ASC);
		for (Record record : records) {
			System.out.println(record);
		}
	}
	
	@Test
	public void testGetUserRecordCount(){
		System.out.println(recordService.getUserRecordCount("1"));
	}

}
