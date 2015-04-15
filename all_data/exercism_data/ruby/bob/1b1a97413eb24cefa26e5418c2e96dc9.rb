class Bob
	def hey(string)
		if string.strip.empty?
			return 'Fine. Be that way!'
		elsif string == string.upcase
			return 'Woah, chill out!'
		elsif string.end_with?('?')
			return 'Sure.'
		else
			return 'Whatever.'
		end
	end
end
