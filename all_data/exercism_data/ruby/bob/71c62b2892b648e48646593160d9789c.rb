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
	def empty?(challenge)
		return challenge.nil? || challenge.empty?
	end

	def question?(challenge)
		return challenge.end_with?('?')
	end

	def yell?(challenge)
		return challenge.upcase == challenge
	end
end
