class Bob
	def hey(word)
		case 
		when word.strip.empty?
			'Fine. Be that way!'
		when word.match(/[a-z]/).nil?
			"Woah, chill out!"
		when !word.match(/[?]$/).nil?
			"Sure."
		else
		  "Whatever."
		end
	end
end
