# Bob
class Bob

	def hey(text)

		if text.empty? or text.delete(" ") == ""
			"Fine. Be that way!"
		elsif text == text.upcase  
			'Woah, chill out!'
		elsif text[-1] == "?"
			"Sure."		
		else
			"Whatever."
		end
	end
end	
