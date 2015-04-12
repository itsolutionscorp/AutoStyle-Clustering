class Phrase
	def initialize(string)
		punctuation_and_spaces = /[^\w']+/
		@phrase = string.downcase.split(punctuation_and_spaces)
	end

	def word_count
		number_of = Hash.new(0)
		@phrase.each { |word| number_of[word] += 1 }
		number_of
	end
end
