class Bob


	def hey(words)
		if (words.upcase == words && words.match("[a-zA-Z]"))
			'Woah, chill out!'
		elsif words.end_with? "?"
			'Sure.'
		elsif words.strip.empty?
			'Fine. Be that way!'
		else
			'Whatever.'
		end
	end

end
