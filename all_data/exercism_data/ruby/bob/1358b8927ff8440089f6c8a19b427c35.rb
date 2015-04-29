class Bob

	def hey(message)
		if  message == nil
			return "Fine. Be that way."
		elsif message.end_with?("?")
			return "Sure."
		elsif message == ""
			return "Fine. Be that way."
		elsif message.upcase == message
			return "Woah, chill out!"
		else
			return "Whatever."			
		end	
	end
end
