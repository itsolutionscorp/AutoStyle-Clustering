class Phrase
	def initialize(phrase)
		@phrase = split phrase
	end

	def word_count
		word_count_hash = {}
		@phrase.each do |word|
			unless word_count_hash[word]
				word_count_hash[word] = @phrase.count(word)
			end
		end
		word_count_hash
	end

	def split(phrase)
		phrase_array = phrase.split(/[^a-zA-Z0-9']+/)
		phrase_array.map { |word| word.downcase }
	end
end
