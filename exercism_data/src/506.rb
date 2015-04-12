class Phrase
	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		words = @phrase.downcase.gsub(/\W/,' ').split(' ')
		counts = Hash.new(0)
		words.each { |word| counts[word] += 1 }
		counts
	end
end
