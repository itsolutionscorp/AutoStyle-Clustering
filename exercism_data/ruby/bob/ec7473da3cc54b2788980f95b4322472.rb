class Bob
	def hey(text)
		if void?(text)
			'Fine. Be that way.'
		else
			responses(text)
		end
	end

	private 

	def void?(text)
		if text.nil? || text.empty?
			return true
		end
	end

	def responses(text)
		if text == text.upcase
			'Woah, chill out!'
		else
			calm_responses(text)
		end
	end

	def calm_responses(text)
		if text.end_with?('.') || text.end_with?('!')
			'Whatever.'
		elsif text.include?('?') && !text.end_with?('?')
			'Whatever.'
		elsif text.end_with?('?')
			'Sure.'
		end
	end

end
