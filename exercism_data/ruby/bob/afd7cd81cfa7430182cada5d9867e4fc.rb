class Bob
	def hey(message)
		if message == "" || message.nil?
			return "Fine. Be that way."
		elsif message.upcase == message
			return "Woah, chill out!"
		elsif message[-1] == "!"
			return "Whatever."
		elsif message[-1] == "?"
			return "Sure."
		else
			return "Whatever."
		end
	end
end
