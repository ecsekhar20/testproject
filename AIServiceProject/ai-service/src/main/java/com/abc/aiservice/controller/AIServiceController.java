package com.abc.aiservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.aiservice.model.InvoiceData;
import com.abc.aiservice.repository.InvoiceRepo;

@RestController
@RequestMapping("/ai-service")
public class AIServiceController {
	@Autowired
	private InvoiceRepo repo;
	
	@PostMapping("/saveinvoice")
	public String saveData(@RequestBody InvoiceData data) {
		repo.save(data);
		return "record saved successfully "+data.getId();
		
	}
	
	@GetMapping("/getAllRecords")
	public List<InvoiceData> getAll(){
		
		return repo.findAll();
	}
	
	@GetMapping("/deletedata/{id}")
	public String deleteRecord(@PathVariable int id) {
		repo.deleteById(id);
		return "record deleted sucessfully.";
	}
	
	
	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer/{id}")
	public String producer(@PathVariable int id) {
		Optional<InvoiceData> data=repo.findById(id);
	
	    if(data.isPresent())
		rabbitMQSender.send(data.get());

		return "Message sent to the RabbitMQ Queue Successfully";
	}


}
