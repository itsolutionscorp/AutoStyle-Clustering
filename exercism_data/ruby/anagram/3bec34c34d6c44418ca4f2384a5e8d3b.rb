class Anagram
	def initialize input
		@input = input.downcase
		@anagrams = []
	end

	def match candidates
		candidates.each { |candidate| @anagrams << candidate if anagram? candidate }
		@anagrams
	end

	def anagram? candidate
		return true if candidate.split('').sort == @input.split('').sort
		false
	end
end
