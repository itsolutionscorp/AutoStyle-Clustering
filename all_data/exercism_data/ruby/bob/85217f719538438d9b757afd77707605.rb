class Bob

	def hey(words)
		return "Fine. Be that way!" if words.strip.empty? == true	
		return "Sure." if words =~ /\?\z/ && words =~ /[a-z]/ || words =~ /\?\z/ && words =~ /\d/	
		return "Woah, chill out!" if words !~ /[a-z]/ && words =~ /[A-Z]/
		"Whatever." 
	end

end
