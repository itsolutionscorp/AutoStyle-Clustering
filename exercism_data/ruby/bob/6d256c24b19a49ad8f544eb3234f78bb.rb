class Bob
	def hey(phrase)
		"Fine. Be that way." if phrase == "" || phrase == nil		
			"Woah, chill out!" if phrase == phrase.upcase		
		"Sure." if phrase.end_with?('?')		
		"Whatever."		
	end
end
