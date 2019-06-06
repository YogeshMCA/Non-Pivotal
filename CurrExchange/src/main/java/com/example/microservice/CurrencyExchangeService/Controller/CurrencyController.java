package com.example.microservice.CurrencyExchangeService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.CurrencyExchangeService.bean.Countries;
import com.example.microservice.CurrencyExchangeService.bean.ExchangeValue;
import com.example.microservice.CurrencyExchangeService.bean.Student;
import com.example.microservice.CurrencyExchangeService.repositories.CountriesRepository;
import com.example.microservice.CurrencyExchangeService.repositories.ExchangeValueRepository;
import com.example.microservice.CurrencyExchangeService.repositories.StudentRepository;
@RestController
public class CurrencyController {
	
	@Autowired
	private Environment environment;
	
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CountriesRepository countryRepository;
	
	@GetMapping(path="/exchange-service/from/{from}/to/{to}")
	public ExchangeValue exchange(@PathVariable String from,@PathVariable String to){
		ExchangeValue exchange = repository.findByFromCodeAndToCode(from, to);
		//System.out.println(environment.getProperty("local.server.port"));
		//exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchange;
	}
	
	@GetMapping(path="/exchange-service-jpa/from/{from}/to/{to}")
	public List<ExchangeValue> exchangeFrom(@PathVariable String from,@PathVariable String to){
		List<ExchangeValue> exchange = repository.fetchExchangeValueData(from,to);
		//System.out.println(environment.getProperty("local.server.port"));
		//exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchange ;
	}

	@GetMapping(path="/exchange-service-jpa-cntry/from/{from}/to/{to}")
	public List<Countries> exchangeValue(@PathVariable String from,@PathVariable String to){
		List<Countries> countries = countryRepository.fetchExValueBetwnCon(from,to);
		//System.out.println(environment.getProperty("local.server.port"));
		//exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return countries ;
	}
	
	@GetMapping(path="/student-info/{id}")
	public List<Student> getStudentInfo(@PathVariable String id){
		List<Student> stdList = studentRepository.findBySId(Long.valueOf(id));
		return stdList;
	}
	
	@PostMapping(path="/save-student-info")
	public String saveStudentInfo(@RequestBody Student student){
		//Long maxId = studentRepository.getMaxsId();
		//student.setsId(++maxId);
		Student std = studentRepository.save(student);
		if(std!=null)
			return "Saved";
		else
			return "Not Saved";
	}

}
