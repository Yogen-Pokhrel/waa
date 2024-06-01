package com.waa.notification;

import com.waa.students.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationCenter {
    public void sendAlert(List<Student> result ){
        // implementation to send alert ...
    }
}
