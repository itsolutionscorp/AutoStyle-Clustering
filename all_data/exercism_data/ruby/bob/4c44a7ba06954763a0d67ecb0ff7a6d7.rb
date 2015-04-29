class Bob
	def hey (speech)
		if speech.lstrip.upcase == speech && speech.length > 2  && !speech.end_with?('3')then
			"Woah, chill out!"
		elsif speech.end_with? '?' then 
			"Sure."
		elsif speech.lstrip == '' then
			"Fine. Be that way!"
		else
		"Whatever."
		end
	end
end
