class Phrase
	def initialize(phrase)
		@clean_phrase = phrase.downcase.delete("^a-z 0-9").split(" ")
	end

	def word_count
		@clean_phrase.inject({}) do |word_count, word|
			word_count[word] ||= 0
			word_count[word] += 1
			word_count
		end
	end
end
