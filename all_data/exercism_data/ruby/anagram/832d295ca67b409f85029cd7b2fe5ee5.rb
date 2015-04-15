class Anagram
	def initialize(word)
		@pattern = word.downcase
		@pattern_sorted = @pattern.chars.sort
	end

	def match(words)
		words.select { |word| anagram?(word.downcase) }
	end

	private
	def anagram?(downcase_word)
		different_from_pattern?(downcase_word) && consist_all_necessary_characters?(downcase_word)
	end

	def different_from_pattern?(downcase_word)
		downcase_word != @pattern
	end

	def consist_all_necessary_characters?(downcase_word)
		downcase_word.chars.sort == @pattern_sorted
	end
end
