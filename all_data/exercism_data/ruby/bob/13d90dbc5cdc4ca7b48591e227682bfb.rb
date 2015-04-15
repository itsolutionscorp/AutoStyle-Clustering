class Bob

	def hey(phrase)
		return "Fine. Be that way!" if !phrase.match(/[[:word:]]/)
		return "Whoa, chill out!" if phrase.match(/[[:alpha:]]/) && phrase == phrase.upcase 
		return "Sure." if phrase[-1] == "?"
		"Whatever."
	end

end
