class Bob
	def hey(phrase)
		return "Fine. Be that way." if phrase == "" || phrase == nil		
		return 	"Woah, chill out!" if phrase == phrase.upcase		
		return "Sure." if phrase.end_with?('?')		
		return "Whatever."		
	end
end
