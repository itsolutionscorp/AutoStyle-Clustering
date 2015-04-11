class Anagram
	def initialize (term)
		@term = term
	end

	def match (match_terms)
		match_terms.select { |word| anagram?(word)}
	end

	def anagram? (input)
		return false if @term.downcase == input.downcase
		@term.downcase.chars.sort == input.downcase.chars.sort
	end
end
