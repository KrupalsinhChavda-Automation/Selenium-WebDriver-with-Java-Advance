package testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestJavaStreams {

	public static void main(String[] args) {

		
		
		
		ArrayList<String> names = new ArrayList<String>();
		
		names.add("Krupalds");
		names.add("Damini");
		names.add("Kulraj");
		names.add("Ayush");
		names.add("Niyati");
		
		// find out the count if names starts with K
		Long c = names.stream().filter(s->s.startsWith("K")).count();
		System.out.println(c);
		
		// to print data as per the filer from the Arraylist
		names.stream().filter(s->s.length()>6).forEach(s->System.out.println(s));
		
		// Limit the results
		names.stream().filter(s->s.length()>3).limit(1).forEach(s->System.out.println(s));
		
		// map the results to UpperCase
		names.stream().filter(s->s.length()>6).map(s->s.toUpperCase()).forEach(s->System.out.println(s));
		
		//sort the data
		List<String> newNames = Arrays.asList("krupal", "Niyati", "krishna", "Nirali", "kanchit");
		newNames.stream().filter(s->s.startsWith("k")).sorted().map(s->s.toUpperCase()).forEach(s-> System.out.println(s)) ;
		
		//merging 2 streams arrays lists
		Stream<String> newstrems = Stream.concat(names.stream(), newNames.stream());
//		newstrems.forEach(s-> System.out.println(s));
		
		//verify data from the lists
		System.out.println( newstrems.anyMatch(s->s.equalsIgnoreCase("krupal")));
		
		//print unique numbers from arra
		List<Integer> values = Arrays.asList(1,4,3,3,4,4,4,5,5,6,6,7,8,9,0);
		
		values.stream().distinct().sorted().forEach(s->System.out.println(s));
		// get 3rd index of above result
		List<Integer> li = values.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(li.get(3));
		
		
		
		Long d = Stream.of("Krupal", "Damini", "Kulraj").filter(s->
		
		{
			s.startsWith("K");
			return true;
		}
				).count();
				
		System.out.println(d);

	}
	
	
}
