class Anagram

	def initialize(detector)
		@detector = detector.downcase
	end

	def match(anagrams)
		sorted_detector = sort_word(@detector)
		
		anagrams.select do |word|
			lower_case_word = word.downcase
			lower_case_word != @detector && sort_word(lower_case_word) == sorted_detector
		end
	end

	def sort_word(word)
		word.split("").sort		
	end
end
