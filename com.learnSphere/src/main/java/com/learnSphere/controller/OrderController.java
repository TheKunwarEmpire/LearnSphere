package com.learnSphere.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnSphere.entities.Course;
import com.learnSphere.entities.Users;
import com.learnSphere.services.TrainerService;
import com.learnSphere.services.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	@Autowired
	TrainerService service;

	@Autowired
	UserService userService;

	@GetMapping("/buyCourses")
	public String buy(Model model) {
		List<Course> courseList= service.courseList();
		model.addAttribute("courses", courseList);
		return "buyCourses";
	}
	@GetMapping("/payment-success")
	public String paymentSuccess(HttpSession session, @RequestParam("courseId") int courseId) {
		System.out.println(courseId);
		String mail =  (String)session.getAttribute("email");
		Users u = userService.getUser(mail);
		Course course = service.getCourse(courseId);
		u.getCourses().add(course);
		u.setHasSubscribed(true);
		course.getUsers().add(u);
		userService.updateUser(u);
		return "studentHome";
	}
	
	@GetMapping("/payment-failure")
	public String paymentFailure() {
		return "myCourses";
	}

	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session) {

		int  amount  = 500;
		Order order=null;
		try {
			RazorpayClient razorpay=new RazorpayClient("rzp_test_UDVi0VD0nfrfwE", "pov8XxYmjI2v1Gsn6nNbajfn");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);

			String mail =  (String) session.getAttribute("email");

			Users u = userService.getUser(mail);
			u.setHasSubscribed(true);
			userService.updateUser(u);

		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
	}
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature,
			@RequestParam int courseId) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_UDVi0VD0nfrfwE", "pov8XxYmjI2v1Gsn6nNbajfn");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "pov8XxYmjI2v1Gsn6nNbajfn");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
