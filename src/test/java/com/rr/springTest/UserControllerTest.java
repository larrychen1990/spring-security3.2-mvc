package com.rr.springTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.rr.springTest.controllers.UserController;

public class UserControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception 
	{
		UserController controller = new UserController();
		MockMvc mockMvc = standaloneSetup(controller)
				.build();
		
		//MvcResult res = mockMvc.perform(get("/users/user/current"))
							   //.andReturn();
		MvcResult res = mockMvc.perform(get("/users/user/admin"))
							   .andReturn();
		
		System.out.println(res.getResponse().getContentAsString());
	}

}
