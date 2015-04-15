class Bob
	def hey (phrase)
		case 
		when phrase.strip.empty?
			"Fine. Be that way!"
		when phrase.end_with?('?')
			"Sure."
		when phrase == phrase.upcase
			"Woah, chill out!"
		else
			"Whatever."
		end
	end
end
