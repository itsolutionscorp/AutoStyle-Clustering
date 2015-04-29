class Bob

	def hey sentence

		if silent? sentence
			"Fine. Be that way!"
		elsif question? sentence
			"Sure."
		elsif yelling? sentence
			"Woah, chill out!"
		elsif statement? sentence
			"Whatever."
		end

	end

	private

	def question? sentence
		sentence[-1] == "?"
	end

	def statement? sentence
		not question? sentence
	end

	def yelling? sentence
		sentence == sentence.upcase
	end

	def silent? sentence
		sentence == "" || sentence == nil
	end

end


