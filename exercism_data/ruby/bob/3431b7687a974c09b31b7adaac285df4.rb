# Bob
class Bob

	def hey(text)
	 	is_it_empty(text) || is_text_shouting_or_question(text)
	end

	private

	def is_it_empty(text)
		if text.empty? or text.delete(" ") == "" 
			"Fine. Be that way!"
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

	
