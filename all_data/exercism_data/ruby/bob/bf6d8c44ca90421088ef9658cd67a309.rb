class Bob

	def hey(message)
		if message == nil || message.empty?
		  return "Fine. Be that way!"
		elsif message == ""
			return "Fine. Be that way!"
		elsif message == message.upcase 
			return "Woah, chill out!"
		elsif message.end_with?("?")
			return "Sure."
		else
		  return "Whatever."
		end
	end

end
