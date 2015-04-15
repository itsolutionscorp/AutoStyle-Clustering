class Bob
	def hey(phrase)

		if is_empty(phrase)
			'Fine. Be that way!'		
		elsif is_shout(phrase)
			'Woah, chill out!'		
		elsif is_question(phrase)
			'Sure.'
		else
			'Whatever.'
		end

	end

	def is_empty(phrase)
		phrase.strip.empty?
	end

	def is_shout(phrase)
		phrase == phrase.upcase
	end

	def is_question(phrase)
		phrase.end_with?('?')
	end
	
end
