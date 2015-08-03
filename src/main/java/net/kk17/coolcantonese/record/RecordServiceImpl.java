package net.kk17.coolcantonese.record;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

@Service(protocol = {"rest"}, group = "annotationConfig", validation = "true")
@Path("records")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
public class RecordServiceImpl implements RecordService {

	@Override
	public Record addRecord(Record record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET
	@Path("{id : \\d+}")
	public Record getRecord(@PathParam("id") String id) {
		Record record = new Record();
		record.setId(id);
		record.setContent("test");
		record.setTime(new Date());
		return record;
	}

	@Override
	public List<Record> getUserRecords(String uid, int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUserRecordCount(String uid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
