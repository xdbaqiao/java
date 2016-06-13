package myHadoop.example;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int num = 0;
		Iterator<IntWritable> valueIts = values.iterator();
		while (valueIts.hasNext()) {
			num += valueIts.next().get();
		}
		context.write(key, new IntWritable(num));
	}
}

