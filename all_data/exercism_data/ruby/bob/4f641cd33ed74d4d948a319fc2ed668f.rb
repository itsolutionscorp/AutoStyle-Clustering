class Bob
	def hey(message)
		message ||= ""
		if message == "" 
			"Fine. Be that way!"
		elsif message.upcase == message
			"Woah, chill out!"
		elsif message[-1] == "?"
			"Sure."
		else
			"Whatever."
		end
	end
end
