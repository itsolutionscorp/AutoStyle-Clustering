class Phrase < String

	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		word_array = @phrase.downcase.scan(/\w+'*\w*/)
		word_array.uniq.each_with_object({}) { |word, hash| hash[word] = word_array.count(word) }
	end

end
