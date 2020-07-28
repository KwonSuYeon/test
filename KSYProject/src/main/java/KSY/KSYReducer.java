package KSY;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KSYReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	private LongWritable result = new LongWritable();

	@Override
	protected void reduce(Text code, Iterable<LongWritable> values,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		long sum=0;
		for(LongWritable value: values) {
			sum+=value.get();
		}
		result.set(sum);
		context.write(code, result);
	}
	

}
