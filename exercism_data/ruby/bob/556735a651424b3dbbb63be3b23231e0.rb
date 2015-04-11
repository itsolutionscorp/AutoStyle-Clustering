# Bob
class Bob



	def hey(text)
		check_empty(text)
		sententce_type(text)
	end

private
	def check_empty(text)
			"Fine. Be that way!" if text.empty? or text.delete(" ") == ""
	end
			
	def sententce_type(text)
		if text == text.upcase  
			'Woah, chill out!'
		elsif text[-1] == "?"
			"Sure."		
		else
			"Whatever."
		end
	end
end	

	
