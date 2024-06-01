package com.waa.aop;
import com.waa.notification.NotificationCenter;
import com.waa.students.entity.Student;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Autowired
    NotificationCenter notificationCenter;

    @Pointcut("execution(org.springframework.http.ResponseEntity<com.waa.helper.ApiResponse<java.util.List<com.waa.students.entity.Student>>> *(..)) && within(com.waa.students.StudentController)")
    public void studentControllerPointCut() {
    }


    @AfterReturning(pointcut = "studentControllerPointCut()", returning = "result")
    public void sendNotification(List<Student> result){
        System.out.println("Sending notification when student lists are fetched");
        notificationCenter.sendAlert( result);
    }
}
