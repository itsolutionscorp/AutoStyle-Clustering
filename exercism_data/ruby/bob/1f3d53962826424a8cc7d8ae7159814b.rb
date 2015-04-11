class Bob


	def hey(sentence)

		capital_letters = sentence[/[A-Z]/]
		lowercase_letters = sentence[/[a-z]/]
		numbers = sentence[/[0-9]/]
		punctuation = sentence[-1]


		if sentence.strip == ""
			return "Fine. Be that way!"
		elsif lowercase_letters && punctuation == "!"
			return "Whatever."
		elsif punctuation == "!"
			return "Woah, chill out!"
		elsif lowercase_letters.nil? && numbers.nil?
			return "Woah, chill out!"
		elsif punctuation == '?'
			return "Sure."
		else
			return "Whatever."
		end

	end




end
