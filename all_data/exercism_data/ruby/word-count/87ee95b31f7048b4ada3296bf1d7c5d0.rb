class Phrase
	def initialize(phrase)
		@clean_phrase = phrase.downcase.delete("^a-z 0-9").split(" ")
	end

	def word_count
		@clean_phrase.inject({}) do |word_count, word|
			if !word_count[word].nil?
				word_count[word] += 1
			else
				word_count[word] = 1
			end
			word_count
		end
	end
end
