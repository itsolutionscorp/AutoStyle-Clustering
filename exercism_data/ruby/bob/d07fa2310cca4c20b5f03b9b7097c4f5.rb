class Bob

	def hey sentence

		if silent? sentence
			"Fine. Be that way!"
		elsif question? sentence
			"Sure."
		elsif yelling? sentence
			"Woah, chill out!"
		else
			"Whatever."
		end

	end

	private

	def question? sentence
		sentence.end_with? "?"
	end

	def yelling? sentence
		sentence == sentence.upcase
	end

	def silent? sentence
		sentence == "" || sentence == nil
	end

end


