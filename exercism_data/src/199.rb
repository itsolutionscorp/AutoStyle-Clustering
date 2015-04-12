class Phrase

	def initialize(phrase)

		@phrase = phrase

	end

	def word_count

		words = @phrase.downcase.scan(/\w+/).map

		frequencies = Hash.new(0)
		words.each { |word| frequencies[word] += 1 }

		return frequencies

	end

end
