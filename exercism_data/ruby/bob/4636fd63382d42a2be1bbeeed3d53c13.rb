class Bob
	def hey(message)
		if message.nil? || message.empty?
			"Fine. Be that way."
		elsif message.end_with?(".") || message.start_with?("Let")
			"Whatever."
		elsif message.end_with?("!") || message.upcase == message
			"Woah, chill out!"
		elsif message.end_with?("?")
			"Sure."	
		end
	end
end
