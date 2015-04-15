class Bob

	def hey statment

		if quiet?(statment)
			return 'Fine. Be that way!'
		elsif exclamatory?(statment) 
			return 'Woah, chill out!'
		elsif question?(statment)
			return 'Sure.'
		else
			return 'Whatever.'
		end

	end

	def quiet? statment
		if statment.strip.empty?
			return true 
		end 
	end

	def exclamatory? statment
		if statment.upcase == statment and statment. =~ /[a-zA-Z]/
			return true 
		end
	end 

	def question? statment
		if statment.end_with? "?"
			return true 
		end
	end 
	
end
