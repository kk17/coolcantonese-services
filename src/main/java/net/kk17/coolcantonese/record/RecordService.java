package net.kk17.coolcantonese.record;

import java.util.List;

public interface RecordService {
	
	Record addRecord(Record record);
	Record getRecord(String id);
	List<Record> getUserRecords(String uid, int limit, int offset);
	long getUserRecordCount(String uid);
	

}
