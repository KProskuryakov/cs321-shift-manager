package edu.gmu.cs321.team3.shiftmanager;



import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void homeTest() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Who Are We?")));
    }
    
    @Test
	public void loginTest() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Please enter your credentials,")));
    }
    
    @Test
	public void registrationTest() throws Exception {
		this.mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Good Choice, Boss!")));
    }
    
    @Test
	public void contactTest() throws Exception {
		this.mockMvc.perform(get("/contact")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Kostyantyn Proskuryakov")));
    }
    
    @Test
	public void dashboardTest() throws Exception {
		this.mockMvc.perform(get("/dashboard")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("USER INFO")));
    }
    
    @Test
	public void orgRegistrationTest() throws Exception {
		this.mockMvc.perform(get("/org_registration")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Create an Organizational Group")));
    }

    @Test
	public void shiftsTest() throws Exception {
		this.mockMvc.perform(get("/shifts")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Start Time End Time Attendees")));
    }
}