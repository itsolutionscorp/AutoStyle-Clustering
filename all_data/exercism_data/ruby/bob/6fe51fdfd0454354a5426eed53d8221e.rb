class Bob
	def hey(message)
		if message == ''
			return 'Fine. Be that way.'
		elsif message == nil
			return 'Fine. Be that way.'
		elsif message.upcase == message || message =~ /\d/
			return 'Woah, chill out!'
		elsif message[-1] == '?'
			return 'Sure.'
		elsif message [-1] == '.'
			return 'Whatever.'
		else
			return 'Whatever.'
		end
	end
end
