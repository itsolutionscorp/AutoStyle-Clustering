#!/usr/bin/ruby

class Bob

	def hey(quote)

		quote.strip!

		if quiet?(quote) 
			'Fine. Be that way!'
		elsif screaming?(quote)
			'Woah, chill out!'    
		elsif asking?(quote)
			'Sure.'      
		else
			'Whatever.'
		end

	end

	private 
	
	def quiet?(quote)
	  quote.empty?
	end

	def screaming?(quote)
	  quote.upcase.eql?(quote)
	end

	def asking?(quote)
	  quote.end_with?('?')
	end

end
