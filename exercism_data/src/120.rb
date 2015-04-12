class Phrase
	def initialize(sentence)
		@words = sentence.downcase.split(/[^a-zA-Z1-9]/)
		@words.delete ''
	end

	def word_count
		Hash[@words.map { |w|
			[w, @words.count(w)]
		}]
	end
end
