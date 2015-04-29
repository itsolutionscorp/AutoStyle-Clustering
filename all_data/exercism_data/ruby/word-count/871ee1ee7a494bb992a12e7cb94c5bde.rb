class Phrase

	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		words = @phrase.downcase.scan(/[\w']+/)
		count = Hash.new(0)
		words.each do |word|
			count[word] += 1
		end
		return count
	end
end
