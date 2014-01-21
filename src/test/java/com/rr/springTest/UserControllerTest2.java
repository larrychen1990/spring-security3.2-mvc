package com.rr.springTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.server.samples.context.WebContextLoader;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rr.springTest.spring.SecurityTestConfig;
import com.rr.springTest.spring.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=SecurityConfig.class)
@ContextConfiguration(classes={SecurityTestConfig.class, WebConfig.class}, 
					  loader=WebDelegatingSmartContextLoader.class)
@WebAppConfiguration
public class UserControllerTest2 
{
	@Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
            .addFilters(this.springSecurityFilterChain).build();
    }

	@Test
	public void test() throws Exception {
		
		mockMvc.perform(get("/users/user/current"))
			   .andExpect(status().isForbidden());

		mockMvc.perform(get("/users/user/admin")
							.accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType("application/json;charset=UTF-8"));

		mockMvc.perform(get("/users/user/admin")
							.accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType("application/json;charset=UTF-8"))
			   .andExpect(content().string("{\"dn\":\"Admin\"}"));
		
		/* This doesn't matter becuase it is stateless...
		UserInfo testInfo = new UserInfo();
		testInfo.setDn("Bob");
		SecUserInfo ud = new SecUserInfo(testInfo);

		PreAuthenticatedAuthenticationToken token =
                new PreAuthenticatedAuthenticationToken(ud, null, ud.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(token);

		mockMvc.perform(get("/users/user/admin")
							.accept(MediaType.APPLICATION_JSON)
							.principal(token))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType("application/json;charset=UTF-8"))
			   .andExpect(content().string("{\"dn\":\"Admin\"}"));
			   */
	}

}
