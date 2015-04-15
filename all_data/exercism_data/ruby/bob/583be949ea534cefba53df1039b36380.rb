class Bob
	def hey something

		if something.strip.empty?
			'Fine. Be that way!'
		elsif something.upcase == something && something != something.downcase
			'Woah, chill out!'
		elsif something.end_with? "?"
			'Sure.'
		else something.end_with? "."
			'Whatever.'
		end
	end
end
