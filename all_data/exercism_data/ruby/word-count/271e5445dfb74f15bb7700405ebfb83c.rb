class Phrase

	attr_accessor :phrase

	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		phrase.downcase.scan(/\w+/).reduce({}) do |accumulator, word|
			accumulator[word] = (accumulator[word] || 0) + 1
			accumulator
		end
	end

end
