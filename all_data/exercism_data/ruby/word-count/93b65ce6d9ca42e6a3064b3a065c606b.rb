class Phrase
    ADDITIONAL_WORD_CHARACTERS = "'"

	def initialize(phrase)
		@phrase = phrase
	end

	def word_count()
		@phrase.downcase.scan(/[\w#{ADDITIONAL_WORD_CHARACTERS}]+/).each_with_object(Hash.new(0)) {|word, counts|
			counts[word] += 1
		}
	end
end
