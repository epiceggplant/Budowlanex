package com.budowlanex.budowlanex;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/workers")
public class WorkerController {
  @Autowired 
  private WorkerRepository workerRepository;

  @PostMapping(path = "/add")
  public @ResponseBody String addWorker(
    @RequestParam String name,
    @RequestParam Integer salary,
    @RequestParam String city,
    @RequestParam String role,
    @RequestParam String address,
    @RequestParam Date dateOfBirth
    ) {

    Worker n = new Worker();
    n.setName(name);
    n.setSalary(salary);
    n.setCity(city);
    n.setRole(role);
    n.setAddress(address);
    n.setDateOfBirth(dateOfBirth);


    workerRepository.save(n);
    return "Saved";
  }

  @PostMapping(path = "/update")
  public @ResponseBody String updateWorker(
    @RequestParam Integer id,
    @RequestParam String name,
    @RequestParam Integer salary,
    @RequestParam String city,
    @RequestParam String role,
    @RequestParam String address,
    @RequestParam Date dateOfBirth
    ) {
    Worker n = workerRepository.findById(id).get();

    n.setName(name);
    n.setSalary(salary);
    n.setCity(city);
    n.setRole(role);
    n.setAddress(address);
    n.setDateOfBirth(dateOfBirth);

    workerRepository.save(n);
    return "Updated";
  }

  @GetMapping(path = "/worker")
  public @ResponseBody Worker getWorker(@RequestParam Integer id) {
    // This returns one worker
    return workerRepository.findById(id).get();
  }

  @DeleteMapping(path = "/worker")
  public @ResponseBody Worker deleteWorker(@RequestParam Integer id) {
    return workerRepository.findById(id).map(worker -> {
      workerRepository.delete(worker);
      return worker;
    }).orElse(null);
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Worker> getAllWorkers() {
    // This returns all workers
    return workerRepository.findAll();
  }

  @GetMapping(path = "/total_salary")
  public @ResponseBody Map<String, Integer> getTotalSalary() {
    Map<String, Integer> map = new HashMap<>();
    map.put("total_salary", workerRepository.getTotalSalary());
    return map;
  }
}