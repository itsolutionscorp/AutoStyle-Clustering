class Bob
	def hey arg
		if arg.strip.empty?
			return 'Fine. Be that way!'
		elsif arg.upcase == arg and arg. =~ /[a-zA-Z]/
			return 'Woah, chill out!'
		elsif arg.end_with? "?"
			return 'Sure.'
		else
			return 'Whatever.'
		end
	end
end
