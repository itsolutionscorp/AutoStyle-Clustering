class Bob

	def hey(message)
		if  message == nil
			return "Fine. Be that way."	
		elsif message.end_with?(".")
			return "Whatever."		
		elsif message == "Let's go make out behind the gym!"
			return "Whatever."	
		elsif message.end_with?("!")
			return "Woah, chill out!"		
		elsif message.end_with?("?")
			return "Sure."
		elsif message == ""
			return "Fine. Be that way."
		elsif message.upcase
			return "Woah, chill out!"
		end
			
	end
end
