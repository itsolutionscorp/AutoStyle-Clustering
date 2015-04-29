class Bob
	def hey(input)
		# Responses
		indifferent 			= 'Whatever.'
		confrontational 	= 'Fine. Be that way.'
	 	chill 						= 'Woah, chill out!'
	 	agreement					= 'Sure.' 

			case 
			when is_nil?(input)
				confrontational
			when all_uppercase?(input)
				chill
			when question?(input)
				agreement
			when digits?(input)
				chill
			when no_frills?(input) 
				indifferent
			else
				confrontational
			end
	end

private 

	def is_nil?(input)
		input == nil
	end

	def all_uppercase?(input)
		input.match( /[A-Z]{2,}/ )
	end

	def question?(input)
		input.match(/[?]$/)
	end

	def digits?(input)
		input.match(/\d/)
	end

	def no_frills?(input)
		input.match(/.+/)
	end

end
