package net.kk17.coolcantonese.record;

import java.util.List;

public interface RecordService {
	
	Record addRecord(Record record);
	Record getRecord(String id);
	long getUserRecordCount(String uid);
	List<Record> getRecords(long startTime, int size, String order);
	List<Record> getUserRecords(String uid, long startTime, int size, String order);
	

}
