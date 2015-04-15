class Anagram
	def initialize (term)
		@term = term
	end

	def match (match_terms)
		match_terms.select { |word| anagram?(word)}
	end

	def anagram? (input)
		return false if @term.downcase == input.downcase
		sort_chars(@term) == sort_chars(input)
	end

	def sort_chars (word)
		word.downcase.chars.sort
	end
end
