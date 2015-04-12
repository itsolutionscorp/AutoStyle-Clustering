class Phrase

 	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
 		@word_counts = prepared_phrase.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
	end

	private

	def prepared_phrase
		@phrase.downcase.split(/\W+/)
	end

end
