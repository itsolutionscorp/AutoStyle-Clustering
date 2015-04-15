class Bob
	def hey(string)
		# Check if nothing is said to bob
		if string.strip.empty?
			return 'Fine. Be that way!'
		# check if bob is being yelled at
		elsif string == string.upcase
			return 'Woah, chill out!'
		# check if bob is being asked a question
		elsif string.end_with?('?')
			return 'Sure.'
		else
			return 'Whatever.'
		end
	end
end
