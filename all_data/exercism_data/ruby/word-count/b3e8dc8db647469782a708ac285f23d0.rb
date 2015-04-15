class Phrase

	def initialize(phrase)
		@phrase = phrase
	end

	def organize_phrase_into_words_with_spaces
		@phrase.downcase.gsub(/\p{^Alnum}/, ' ').split(" ")
	end

	def word_count
		organize_phrase_into_words_with_spaces.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
	end

	
end
