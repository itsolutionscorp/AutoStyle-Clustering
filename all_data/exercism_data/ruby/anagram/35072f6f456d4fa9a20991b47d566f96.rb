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
		candidate_temp = candidate.split('')
		@input.split('').permutation do |input_perm|
			if input_perm == candidate_temp
				return true
			end
		end
		false
	end
end
