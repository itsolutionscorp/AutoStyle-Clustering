class Phrase

	def initialize(phrase)

		@phrase = phrase.downcase.gsub /\W+/, ' '

	end

	def word_count

		words = @phrase.split(/ /)

		frequencies = Hash.new(0)
		words.each { |word| frequencies[word] += 1 }

		return frequencies

	end

end
