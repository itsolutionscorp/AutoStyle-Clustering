class Phrase
	def initialize(phrase)
		@phrase = phrase.downcase.split.delete(":,!&@$%^")
	end
	def word_count
 		@phrase.inject(Hash.new(0)) { |h,v| h[v] += 1; h }
	end
end
