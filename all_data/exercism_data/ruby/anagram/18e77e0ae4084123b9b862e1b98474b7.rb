class Anagram
	def initialize word
		@word = word
	end

	def match words
		words.select {|w|
			downcase_word = w.downcase
			downcase_word.chars.sort == @word.downcase.chars.sort && 
				downcase_word != @word && 
				downcase_word != @word.reverse
		}
	end
end
