class Bob
	def hey(message)
		if message == '' || message == nil
			return 'Fine. Be that way.'
		elsif message == message.upcase
			return 'Woah, chill out!'
		elsif message.end_with?("?")
			return 'Sure.'
		else
			return 'Whatever.'

		end
	return "Whatever."
	end
end
