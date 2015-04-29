class Bob
	
	def hey(sentence)
		case 
		when silence?(sentence)
			'Fine. Be that way.'
		when question?(sentence)
			'Sure.'
		when shouting?(sentence)
			'Woah, chill out!'
		else 
			'Whatever.'
		end
	end

	private
	def silence?(sentence)
		sentence.nil? || sentence == ''
	end

	def question?(sentence)
		sentence.end_with?('?')
	end

	def shouting?(sentence)
		sentence == sentence.upcase
	end

end
