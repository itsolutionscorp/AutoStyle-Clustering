class Bob

	def hey sentence

		if question? sentence
			"Sure."
		elsif yelling? sentence
			"Woah, chill out!"
		elsif statement? sentence
			"Whatever."
		elsif sentence == ""
			"Fine. Be that way!"
		end

	end

	private

	def question? sentence
		sentence.match(/\?/)
	end

	def statement? sentence
		not question? sentence
	end

	def yelling? sentence
		(!sentence.match(/[a-z]/)) && (sentence.match(/[A-Z]/))
	end

end


