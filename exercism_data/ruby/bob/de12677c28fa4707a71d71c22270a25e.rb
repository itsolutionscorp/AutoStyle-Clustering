class Bob

	def hey statment

		if quiet?(statment)
			'Fine. Be that way!'
		elsif exclamatory?(statment) 
			'Woah, chill out!'
		elsif question?(statment)
			'Sure.'
		else
			'Whatever.'
		end

	end

	def quiet? statment
		statment.strip.empty?
	end

	def exclamatory? statment
		statment.upcase == statment and statment. =~ /[a-zA-Z]/
	end 

	def question? statment
		statment.end_with? "?"
	end 
	
end
