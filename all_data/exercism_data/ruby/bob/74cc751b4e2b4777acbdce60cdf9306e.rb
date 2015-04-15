class Bob
	def hey(message)
		if message.end_with? "?"
			return 'Sure.'
		elsif message.upcase == message
			return 'Woah, chill out!'
		elsif message == ''
			return 'Fine. Be that way!'
		end
		'Whatever.'
	end
end
