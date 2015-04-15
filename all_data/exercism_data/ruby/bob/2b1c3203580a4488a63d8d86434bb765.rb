class Bob
	def hey(message)
		if message.strip.empty?
			"Fine. Be that way!"
		elsif is_yelling?(message)
			"Woah, chill out!"
		elsif message.end_with?("?")
			"Sure."
		else
			"Whatever."
		end
	end
	
	def is_yelling?(message)
		message.upcase == message and message.match(/[A-Z]/)
	end

end
