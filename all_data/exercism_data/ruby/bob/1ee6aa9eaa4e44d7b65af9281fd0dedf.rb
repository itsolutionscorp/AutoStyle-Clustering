class Bob
	NOTHING_REGEX = /\A\s*\Z/
	YELLING_REGEX = /\A[^a-z]*[A-Z][^a-z]*\Z/
	QUESTION_REGEX = /.+\?\Z/

	def hey(words)
		case words
		when NOTHING_REGEX
			'Fine. Be that way!'
		when YELLING_REGEX
			'Woah, chill out!'
		when QUESTION_REGEX
			'Sure.'
		else
			'Whatever.'
		end
	end
end
