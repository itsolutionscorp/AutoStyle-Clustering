class Bob
	def hey(sentence)
		if (isEmpty?(sentence))
			'Fine. Be that way.'
		elsif (isQuestion?(sentence))
			'Sure.'
		elsif (isUpcase?(sentence))
			'Woah, chill out!'
		else
			'Whatever.'
		end
	end

	private

	def isQuestion?(sentence)
		sentence.end_with?('?')
	end

	def isUpcase?(sentence)
		sentence == sentence.upcase
	end

	def isEmpty?(sentence)
		sentence.nil? || sentence.empty?
	end
end
