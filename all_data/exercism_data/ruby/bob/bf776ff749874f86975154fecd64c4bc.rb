class Bob
	def hey(message)
		message.strip!
		
		if message =~ /[A-Z]+/ and message !~ /[a-z]/
			return 'Woah, chill out!'
		elsif message[-1] == '?'
			return 'Sure.'
		elsif message.empty?
			return 'Fine. Be that way!'
		else
			return 'Whatever.'
		end
	end
end
