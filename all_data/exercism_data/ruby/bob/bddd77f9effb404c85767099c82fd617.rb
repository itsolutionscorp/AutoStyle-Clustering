class Bob
	def hey(phrase)
		if phrase == phrase.upcase && phrase.downcase != phrase
			'Woah, chill out!'
		elsif phrase.end_with? '?'
			'Sure.'
		elsif phrase.to_s.strip.empty?
			'Fine. Be that way!'
		else
			'Whatever.'
		end
	end
end
