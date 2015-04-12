class Phrase
	def initialize(phrase)
		@phrase = phrase.delete(":,!&@$%^")
	end
	def word_count
 		@phrase.downcase.split.inject(Hash.new(0)) { |h,v| h[v] += 1; h }
	end
end
