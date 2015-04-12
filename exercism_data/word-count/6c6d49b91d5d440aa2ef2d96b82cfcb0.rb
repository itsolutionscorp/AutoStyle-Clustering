class Phrase
	def initialize(phrase)
		@phrase = phrase.chomp.downcase.split(/\W+/)
	end

	def word_count
		words = @phrase
		counts = Hash.new(0)

		words.each { |word| counts[word] += 1 }
		return counts
	end
end
