class Bob
	def hey(words)
		if words =~ /[a-zA-Z]/ && words == words.upcase
			'Woah, chill out!'
		elsif words.end_with?("?")
			'Sure.'
		elsif words.rstrip.empty? 
			'Fine. Be that way!'
		else
			'Whatever.'
		end
	end
end
