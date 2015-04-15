class Bob
	def initialize
	end
	def hey (speech)
		if speech.lstrip == '' then
			"Fine. Be that way!"
		elsif
			speech.upcase == speech && speech.length > 2  && !speech.end_with?('3') then
			"Woah, chill out!"
		elsif speech.end_with? '?' then 
			"Sure."
		else
		"Whatever."
		end
	end
end
