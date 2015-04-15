class Bob
	def hey(input)

		if input.gsub(/\s+/, '') == ''
			# He says 'Fine. Be that way!' if you address him without actually saying anything.
			answer = 'Fine. Be that way!'
		elsif input.match(/[a-zA-z]/) && input.upcase == input
			# He answers 'Woah, chill out!' if you yell at him.
			answer = 'Woah, chill out!'
		elsif input.split('').last == '?'
			# Bob answers 'Sure.' if you ask him a question.
			answer = 'Sure.'
		else
			# He answers 'Whatever.' to anything else.
			answer = 'Whatever.'
		end

		answer
	end
end
