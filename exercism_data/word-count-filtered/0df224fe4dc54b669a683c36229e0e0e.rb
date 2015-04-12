class Phrase
	def initialize(words)
		@words = words.chomp.downcase.split(/\W+/)
	end

	def word_count
		words = @words
		counts = Hash.new(0)

		words.each { |word| counts[word] += 1 }
		return counts
	end
end
