class Phrase

	def initialize(phrase)

		@phrase = phrase

	end

	def word_count

		words = @phrase.scan(/\w+/).map { |w| w.downcase }

		frequencies = Hash.new(0)
		words.each { |word| frequencies[word] += 1 }

		return frequencies

	end

end
