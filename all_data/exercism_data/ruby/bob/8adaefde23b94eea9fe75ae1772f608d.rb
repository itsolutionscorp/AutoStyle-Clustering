class Bob

	def hey string

		sentence = Sentence.new(string)

		if sentence.empty?
			'Fine. Be that way.'
		elsif sentence.yelling?
			'Woah, chill out!'
		elsif sentence.question?
			'Sure.'
		else
			'Whatever.'
		end

	end

end


class Sentence

	def initialize string
		@string = string
	end

	def empty?
		@string.nil? || @string.empty?
	end

	def yelling?
		@string.upcase == @string
	end

	def question?
		@string.end_with?('?')
	end

end
