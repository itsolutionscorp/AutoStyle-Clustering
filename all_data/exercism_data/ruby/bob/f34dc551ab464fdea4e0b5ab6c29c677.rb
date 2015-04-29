class Bob
	def hey(message)
		sentence = Sentence.new(message)
		if sentence.quiet?
			'Fine. Be that way.'
		elsif sentence.yelling?
			'Woah, chill out!'
		elsif sentence.questioning?
			'Sure.'
		else 
			'Whatever.'
		end
	end
end

class Sentence
	def initialize(message)
		@sentence = sentence
	end

	def quiet?
		sentence.nil? || sentence.empty?
	end

	def yelling?
		sentence == sentence.upcase
	end

	def questioning?
		sentence.end_with?('?')
	end

	private
	attr_reader :sentence
end
