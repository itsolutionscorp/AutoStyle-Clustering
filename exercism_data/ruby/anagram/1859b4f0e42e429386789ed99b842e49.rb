class Anagram
	def initialize input
		@input = input.downcase.chars
	end

	def match candidates
		candidates.select { |candidate| anagram? candidate }
	end

	def anagram? candidate
		candidate.chars.sort == @input.sort
	end
end
