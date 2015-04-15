class Bob
	def initialize
	end

	def hey message
		if message !~ /[^[:space:]]/
			"Fine. Be that way!"
		elsif message.upcase == message
			"Woah, chill out!"	
		elsif message.end_with?("?")	
			"Sure."
		else
			"Whatever."
		end
	end
end
