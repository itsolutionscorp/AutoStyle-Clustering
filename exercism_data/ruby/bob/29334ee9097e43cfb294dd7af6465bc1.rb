class Bob
	def hey(words)
        return 'Fine. Be that way!' if words == nil  || words.strip.empty?  
        return 'Woah, chill out!' if words.upcase == words
		return 'Sure.' if words.rstrip.end_with? "?"
		'Whatever.'
	end
end
