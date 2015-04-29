class Anagram
	def initialize word
		@word = word
		@chars = word.downcase.chars.sort
	end

	def match words
		words.to_a.select {|w|
			downcase_word = w.downcase
			downcase_word.chars.sort == @chars && 
				downcase_word != @word && 
				downcase_word != @word.reverse
		}
	end
end
