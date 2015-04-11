class Bob
	def hey(phrase)
		case 
		when phrase.strip.empty?
			'Fine. Be that way!'
		when phrase.upcase == phrase && phrase.match(/[a-zA-Z]/)
			'Whoa, chill out!'
		when phrase[phrase.length-1] == '?'
			'Sure.'	
		else
			'Whatever.'
		end
	end
end
