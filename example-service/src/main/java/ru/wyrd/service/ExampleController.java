package ru.wyrd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wyrd.limiter.Limit;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    @GetMapping("/")
    @Limit
    public String test() {
        return "test";
    }
}
