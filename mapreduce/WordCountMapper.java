package myHadoop.example;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<IntWritable, Text, Text, IntWritable> {
	private final IntWritable one = new IntWritable(1);
	private Text text = new Text();

	@Override
	protected void map(IntWritable key, Text value, Mapper<IntWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String[] csvs = key.toString().split(",");
		for (String csv : csvs) {
			text.set(csv);
			context.write(text, one);
		}
	}
}

