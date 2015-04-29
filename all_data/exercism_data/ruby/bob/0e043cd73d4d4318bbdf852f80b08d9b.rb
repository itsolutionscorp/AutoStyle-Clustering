# Bob
class Bob

	def hey(text)
		if is_it_empty(text)
			"Fine. Be that way!"
		else
			is_text_shouting_or_question(text)
		end
	end

	private

	def is_it_empty(text)
		if text.empty? or text.delete(" ") == "" 
			true
		end	
	end

	def is_text_shouting_or_question(text)
		if text == text.upcase  
			'Woah, chill out!'
		elsif text[-1] == "?"
			"Sure."		
		else
			"Whatever."	
		end	
	end
end	

	
