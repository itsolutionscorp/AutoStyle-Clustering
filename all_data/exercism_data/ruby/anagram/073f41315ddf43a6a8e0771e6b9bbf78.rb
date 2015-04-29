class Anagram
	def initialize(word)
		@word = word.downcase
	end

	def match(candidates)
		sorted_word = @word.chars.sort
		candidates
			.select {|candidate| candidate.downcase != @word}
			.select {|candidate| candidate.downcase.chars.sort == sorted_word}
	end
end
