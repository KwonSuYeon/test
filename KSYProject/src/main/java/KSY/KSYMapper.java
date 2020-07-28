package KSY;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class KSYMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	private final static LongWritable time = new LongWritable(1);
	private Text code = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		KSYAirline al = new KSYAirline(value);
		if(al.isTimeAvailable()) {
			if(al.getActualElapsedTime()>0) {
				code.set(al.getUniqueCarrier());
				time.set(al.getActualElapsedTime());
				context.write(code, time);
			}
		}

	}
	
}
