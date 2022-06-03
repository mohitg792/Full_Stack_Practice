package full_stack.intro;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GreeterController_urls {
	//localhost:8989/hello1/Mohit
	@GetMapping("/hello1/{name}")
	public String sayHello(@PathVariable String name ) {		
		return "Hello "+name;
	}
	//localhost:8989/hello2?name=Mohit
	@GetMapping("/hello2")
	public String sayHelloToMe(@RequestParam String name ) {		
		return "Say Hello "+name;
	}
	//localhost:8989/add?num1=10&num2=20
	@GetMapping("/add")	
	public int add(@RequestParam String num1,String num2 ) {		
		return Integer.parseInt(num1) + Integer.parseInt(num2);
	}
	
	@GetMapping("/getemp")
	public Employee_methods getEmployee() {		
		return new Employee_methods(123,"Mohit",34344);
	}
	
	@PostMapping("/saveemp")	
	// DB insertion code
	public String saveEmployee(@RequestBody Employee_methods emp) {	
		
		return "Inserted "+ emp.getEno();
	}
	
	@PutMapping("/updateemp")	
	public String updateEmployee(@RequestBody Employee_methods emp) {	
		
		return "Update "+ emp.getEno();
	}
	
	
	@DeleteMapping("/removeemp")	
	public String removeEmployee(@RequestParam int eno) {	
		//database return value
		return "Record Deleted: "+ eno;
	}
	

}
