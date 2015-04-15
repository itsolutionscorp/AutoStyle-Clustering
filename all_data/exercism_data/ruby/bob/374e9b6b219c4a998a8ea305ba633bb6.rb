class Bob
	def hey (string)
		return 'Woah, chill out!' if string.match(/[a-zA-Z]/) && string == string.upcase
		
    
    return 'Sure.' if string.end_with?("?")
    
    return 'Fine. Be that way!' if string.strip.empty?

		"Whatever."
	end
end
