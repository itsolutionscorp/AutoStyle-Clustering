class Bob
	def hey(message)
		if message =~ /[A-Z]+/ and message !~ /[a-z]/
			return 'Woah, chill out!'
		elsif message[-1,1] == '?'
			return 'Sure.'
		elsif message.gsub("\n", '') =~ /^ *$/
			return 'Fine. Be that way!'
		else
			return 'Whatever.'
		end
	end
end
