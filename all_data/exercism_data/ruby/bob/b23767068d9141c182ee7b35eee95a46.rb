class Bob
	def hey(input= 'none')
		responses = { indifferent: 'Whatever.', confrontational: 'Fine. Be that way.', chill: 'Woah, chill out!', agreement: 'Sure.' }

			case 
			when input == nil
				responses[:confrontational]
			when input.match( /[A-Z]{2,}/ )
				responses[:chill]
			when input.match(/[?]$/)
				responses[:agreement]
			when input.match(/\d/)
				responses[:chill]
			when input.match(/.+/)	
				responses[:indifferent]
			else
				responses[:confrontational]
			end
	end
end
