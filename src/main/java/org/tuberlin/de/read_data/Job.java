package org.tuberlin.de.read_data;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;


/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.flink.util.Collector;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Skeleton for a Flink Job.
 *
 * For a full example of a Flink Job, see the WordCountJob.java file in the
 * same package/directory or have a look at the website.
 *
 * You can also generate a .jar file that you can submit on your Flink
 * cluster.
 * Just type
 * 		mvn clean package
 * in the projects root directory.
 * You will find the jar in
 * 		target/flink-quickstart-0.1-SNAPSHOT-Sample.jar
 *
 */
public class Job {

	public static DataSet<Taxidrive> readInput(ExecutionEnvironment env, String dataPath) throws Exception {

	  	DataSet<String> textInput =  env.readTextFile(dataPath);
		DataSet<Taxidrive> taxidriveDataSet = textInput.flatMap(new TaxidriveReader());

		/**
		 * Here, you can start creating your execution plan for Flink.
		 *
		 * Start with getting some data from the environment, like
		 * 	env.readTextFile(textPath);
		 *
		 * then, transform the resulting DataSet<String> using operations
		 * like
		 * 	.filter()
		 * 	.flatMap()
		 * 	.join()
		 * 	.coGroup()
		 * and many more.
		 * Have a look at the programming guide for the Java API:
		 *
		 * http://flink.apache.org/docs/latest/programming_guide.html
		 *
		 * and the examples
		 *
		 * http://flink.apache.org/docs/latest/examples.html
		 *
		 */

		// execute program
		return taxidriveDataSet;
	}




  	public static final class TaxidriveReader implements FlatMapFunction<String, Taxidrive> {

	  @Override public void flatMap(String value, Collector<Taxidrive> out)
			  throws Exception {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Taxidrive taxidrive = new Taxidrive();

		  try {
			  String[] splittedText = value.split(",");
			  taxidrive.setTaxiID(splittedText[0]);
			  taxidrive.setLicenseID(splittedText[1]);
			  taxidrive.setPickup_datetime(splittedText[2]);
			  taxidrive.setDropoff_datetime(splittedText[3]);
			  taxidrive.setTrip_time_in_secs(Integer.parseInt(splittedText[4]));
			  taxidrive.setTrip_distance(Double.parseDouble(splittedText[5]));
			  taxidrive.setPickup_longitude(Double.parseDouble(splittedText[6]));
			  taxidrive.setPickup_latitude(Double.parseDouble(splittedText[7]));
			  taxidrive.setDropoff_longitude(Double.parseDouble(splittedText[8]));
			  taxidrive.setDropoff_latitude(Double.parseDouble(splittedText[9]));
			  taxidrive.setPayment_type(splittedText[10]);
			  taxidrive.setFare_amount(Double.parseDouble(splittedText[11]));
			  taxidrive.setSurcharge(Double.parseDouble(splittedText[12]));
			  taxidrive.setMta_tax(Double.parseDouble(splittedText[13]));
			  taxidrive.setTip_amount(Double.parseDouble(splittedText[14]));
			  taxidrive.setTolls_amount(Double.parseDouble(splittedText[15]));
			  taxidrive.setTotal_amount(Double.parseDouble(splittedText[16]));
			  out.collect(taxidrive);
		  } catch (Exception e){

		  }
	  }
	}
}
