class Phrase
	def initialize(string)
		@string = string
	end

	def words
		@string.scan /\w+/
	end

	def normalized_words
		words.map &:downcase
	end

	def word_count
		counts_hash = normalized_words.group_by { |word| word }
		counts_hash.each { |word, occurrences| counts_hash[word] = occurrences.count }
		counts_hash
	end
end
