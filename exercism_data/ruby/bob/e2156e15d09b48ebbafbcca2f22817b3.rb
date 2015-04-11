class Bob
	def hey(message)
		if message == nil
			"Fine. Be that way."
		elsif message.length == 0
			"Fine. Be that way."
		elsif message.upcase == message
			"Woah, chill out!"
		elsif message.end_with?("?")
			"Sure."
		elsif message.end_with?(".") || message.end_with?("!")
			"Whatever."
		
		end 
	end 
end 
