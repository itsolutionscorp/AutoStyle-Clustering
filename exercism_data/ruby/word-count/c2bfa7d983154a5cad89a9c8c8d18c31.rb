class Phrase

 	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
 		@word_counts = prepared_phrase.inject(Hash.new(0)) do |counts, word|
 			counts[word] += 1 
 			counts
 		end
	end

	private

	def prepared_phrase
		@phrase.downcase.split(/\W+/)
	end

end
