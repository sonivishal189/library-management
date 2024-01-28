package vishal.test.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @GetMapping("/")
    public String welcome() {
        return "Hi!!! Welcome to Genius Library";
    }
}
