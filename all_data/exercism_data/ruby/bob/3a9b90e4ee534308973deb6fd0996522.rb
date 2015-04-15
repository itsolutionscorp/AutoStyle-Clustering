class Bob

	def hey message
		if message.to_s.empty?
			'Fine. Be that way.'
		elsif message.to_s.end_with? '?'
			'Sure.'
		elsif message == message.upcase
			'Woah, chill out!'
		else 
			'Whatever.'
		end
	end

end
