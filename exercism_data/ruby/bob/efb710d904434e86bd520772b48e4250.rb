#!/usr/bin/ruby

class Bob

	def hey(quote)

	 	quote.strip!

		if is_empty?(quote) 
			'Fine. Be that way!'
		elsif is_screaming?(quote)
			'Woah, chill out!'    
		elsif is_a_question?(quote)
			'Sure.'      
		else
			'Whatever.'
		end

	end

	private 
	
	def is_empty?(quote)
		quote.empty?
	end

	def is_screaming?(quote)
		quote.upcase.eql?(quote)
	end

	def is_a_question?(quote)
		quote.end_with?('?')
	end


end
