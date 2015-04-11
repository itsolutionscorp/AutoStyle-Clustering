#!/usr/bin/ruby

class Bob

	def hey(quote)

	 	quote.strip!

		if quote.length == 0 
			return 'Fine. Be that way!' if quote.length < 1
		elsif quote.upcase.eql?(quote)
			return 'Woah, chill out!'    
		elsif quote.slice(-1).include?('?')
			return 'Sure.'      
		else
			'Whatever.'
		end

	end

end
