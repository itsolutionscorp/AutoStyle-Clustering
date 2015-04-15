class Bob
	def hey(challenge)
		if is_empty?(challenge) 
			response = 'Fine. Be that way.'
		elsif is_yell?(challenge) 
			response = 'Woah, chill out!'
		elsif is_question?(challenge) 
			response = 'Sure.'
		else
			response = 'Whatever.'
		end
		response
	end

	private
	def is_empty?(challenge)
		return challenge.nil? || challenge.empty?
	end

	def is_question?(challenge)
		return challenge.end_with?('?')
	end

	def is_yell?(challenge)
		return challenge.upcase == challenge
	end
end
