class Bob
	def hey(sentence)
		if sentence.strip.empty?  
			'Fine. Be that way!'
		elsif  sentence =~ /\p{Upper}/ && sentence == sentence.upcase
			'Woah, chill out!'
		elsif sentence.end_with?('?')
			'Sure.'
		else
			"Whatever."
		end
	end
end