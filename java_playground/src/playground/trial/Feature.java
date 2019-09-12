package playground.trial;

import java.util.function.Function;

public class Feature {

	private Feature() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Function<String, String> append = (var string) -> string + " World";
		String appendedString = append.apply("Hello");
		System.out.println(appendedString);
		
		Function<Double, Double> interest = (var d) -> d/10;
		Double res = interest.apply( 100.0);
		System.out.println(res);
 	}

}
