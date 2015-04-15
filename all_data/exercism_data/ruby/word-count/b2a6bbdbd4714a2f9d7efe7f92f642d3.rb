class Phrase

	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		organize_phrase.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
	end

	def organize_phrase
		@phrase.downcase.gsub(",", " ").gsub(/\p{^Alnum}/, ' ').split(" ")
	end
end
