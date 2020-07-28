package KSY;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class KSYMain {
//항공사별 총 운행시간 데이터 구하기
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = new Job(conf,"KSYMain");
		String in = "D:/ubuntu_share/dataexpo/1990.csv";
		String out = "outfile/KMSActualElapsedTime-1990";
		FileInputFormat.addInputPath(job, new Path(in));
		FileOutputFormat.setOutputPath(job, new Path(out));
		FileSystem hdfs = FileSystem.get(conf);
		if(hdfs.exists(new Path(out))) {
			hdfs.delete(new Path(out),true);
			System.out.println("기존 출력파일 삭제");
		}
		job.setJarByClass(KSYMain.class);
		job.setMapperClass(KSYMapper.class);
		job.setReducerClass(KSYReducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job.waitForCompletion(true);
	}

}
