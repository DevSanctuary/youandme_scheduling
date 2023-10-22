package com.project.youandme_schedule.friend;

import com.project.youandme_schedule.dto.FriendDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestBody FriendDto dto) {
        String message = friendService.addFriend(dto);
        return ResponseEntity.ok(message);
    }
}
