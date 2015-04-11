class Bob
	def hey(message)
		if message == nil
			'Fine. Be that way.'
	    elsif message.length == 0
			"Fine. Be that way."
		elsif message.upcase == message
			"Woah, chill out!"
		elsif message.end_with?("?") 
			"Sure."
		else
		  "Whatever."
		end
	end
end
