class Bob

	def hey(sentence)

		capital_letters = sentence[/[A-Z]/]
		lowercase_letters = sentence[/[a-z]/]
		numbers = sentence[/[0-9]/]
		punctuation = sentence[-1]

		if sentence.strip == ""
			"Fine. Be that way!"
		elsif lowercase_letters.nil? && (punctuation == '!'|| numbers.nil?)
			"Woah, chill out!" 
		elsif punctuation == '?'
			"Sure."
		else
			"Whatever."
		end

	end

end
