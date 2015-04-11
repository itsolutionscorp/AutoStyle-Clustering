class Bob

	def hey(words)
		if words.strip.empty? == true
			"Fine. Be that way!"
		elsif words =~ /\?\z/ && words =~ /[a-z]/
			"Sure."
		elsif words =~ /\?\z/ && words =~ /\d/
			"Sure."
		elsif words !~ /[a-z]/ && words !~ /[A-Z]/
			"Whatever." 
		elsif words !~ /[a-z]/ 
			"Woah, chill out!" 
		else
			"Whatever." 
		end
	end

end
