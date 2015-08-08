package net.kk17.coolcantonese.record;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import net.kk17.common.QueryOrder;

@Service(protocol = { "rest" }, group = "annotationConfig", validation = "true")
@Path("/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
public class RecordServiceImpl implements RecordService {
	@Autowired
	private Datastore datastore;

	@Override
	@POST
	@Path("/record")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Record addRecord(Record record) {
		if (record.getTime() == null) {
			record.setTime(new Date());
		}
		Record reply = record.getReply();
		if (reply != null) {
			if (reply.getTime() == null) {
				reply.setTime(new Date());
			}
			datastore.save(reply);
		}
		datastore.save(record);
		return record;
	}

	@Override
	@GET
	@Path("/record/{id}")
	public Record getRecord(@PathParam("id") String id) {
		Record record = datastore.createQuery(Record.class).field("id").equal(new ObjectId(id)).get();
		return record;
	}

	@Override
	@GET
	@Path("/records")
	public List<Record> getRecords(@QueryParam("start-time") @DefaultValue("0") long startTime,
			@QueryParam("size") @DefaultValue("10") int size,
			@QueryParam("order") @DefaultValue(QueryOrder.DESC) String order) {
		Query<Record> query = datastore.createQuery(Record.class);
		if (order == QueryOrder.ASC) {
			query.field("time").greaterThan(new Date(startTime)).order("time");
		} else {
			if (startTime == 0) {
				startTime = Long.MAX_VALUE;
			}
			query.field("time").lessThan(new Date(startTime)).order("-time");
		}
		List<Record> records = query.limit(size).asList();
		return records;
	}

	@Override
	@GET
	@Path("/user/{uid}/records")
	public List<Record> getUserRecords(@PathParam("uid") String uid,
			@QueryParam("start-time") @DefaultValue("0") long startTime,
			@QueryParam("size") @DefaultValue("10") int size,
			@QueryParam("order") @DefaultValue(QueryOrder.DESC) String order) {
		Query<Record> query = datastore.createQuery(Record.class).field("uid").equal(uid);
		if (order == QueryOrder.ASC) {
			query.field("time").greaterThan(new Date(startTime)).order("time");
		} else {
			if (startTime == 0) {
				startTime = Long.MAX_VALUE;
			}
			query.field("time").lessThan(new Date(startTime)).order("-time");
		}
		List<Record> records = query.limit(size).asList();
		return records;
	}

	@Override
	public long getUserRecordCount(String uid) {
		long count = datastore.createQuery(Record.class).field("uid").equal(uid).countAll();
		return count;
	}

}
