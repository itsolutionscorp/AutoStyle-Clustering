class Phrase

	def initialize(phrase)

		@phrase = phrase

	end

	def word_count

		get_words_from_phrase().each_with_object({}) { |word, freq| increment_word_frequency(word, freq) }

	end

private

	def get_words_from_phrase
		@phrase.downcase.scan(/\w+/)
	end

	def increment_word_frequency(word, freq)
		freq[word] = freq[word].nil? ? 1 : freq[word] + 1
	end 


end
