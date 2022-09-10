package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World thru get";
	}
	
	//hello-world/path-variable/in28minutes
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("hello %s", name));
	}
	
	//@GetMapping(path="/hello-world/myFunc/{selectedYear}/{amount}/{percentage}")
	//@GetMapping(path="/hello-world/request")
	//@RequestMapping(path="/message", produces=MediaType.TEXT_PLAIN_VALUE)
	@GetMapping(path="/request")
	//public static String myFunc(@PathVariable int selectedYear,@PathVariable double amount,@PathVariable float percentage) {
	public static String myFunc(@RequestParam(required = true) int selectedYear,@RequestParam (required = true) double amount,@RequestParam(required = true) float percentage) {
		double grandTotal = 0;
		double totalAmount = 0;
		//double greatgrandTotal = 0;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int count = currentYear - selectedYear;
		while (count > 0) {
			for (int i = 0; i < count; i++) {
				if (totalAmount == 0) {
					totalAmount = amount / 100 * percentage;
					totalAmount = totalAmount + amount;
				} else {
					totalAmount = grandTotal / 100 * percentage;
				}
				grandTotal = totalAmount + grandTotal;
			}
			count--;
			totalAmount = 0;
		}
		//System.out.println(
				//" greatgrandTotal Cumulative Interest from " + selectedYear + " for the amount is " + grandTotal);

		return "Grand Total for the amount: "  + grandTotal;

	}

}
