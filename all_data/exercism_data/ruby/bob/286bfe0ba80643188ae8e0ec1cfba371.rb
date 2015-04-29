class Bob
	def hey(message)
		#To avoid undefined method for nil class errors
		message ||= ""

		if message == ""
			return "Fine. Be that way."
		elsif message.upcase == message
			return "Woah, chill out!"
		elsif message.end_with?("?")
			return "Sure."
		else
			return "Whatever."
		end
	end
end
