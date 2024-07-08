package com.redmath.myfirst.homeController;
import java.util.Map;
import java.util.Objects;
import java.util.random.RandomGenerator;
import  org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {
    @GetMapping("/api/v1/random")
    public ResponseEntity<Map<String , Object>> home(){
        return ResponseEntity.ok(Map.of("message","hello","random",RandomGenerator.getDefault()));
    }
}
