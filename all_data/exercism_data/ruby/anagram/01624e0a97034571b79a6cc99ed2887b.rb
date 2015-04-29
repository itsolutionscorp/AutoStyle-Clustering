class Anagram
	def initialize(base_word)
		@base_word = base_word
	end

	def match(possible_anagrams)
		possible_anagrams.each_with_object([]) do |possible_anagram, anagrams|
			if letter_count_of(@base_word) == letter_count_of(possible_anagram)
				anagrams << possible_anagram
			end
		end
	end

	def letter_count_of(word)
		word.split("").each_with_object({}) do |letter, letter_count_hash|
			letter_count_hash[letter] ||= 0
			letter_count_hash[letter] += 1
		end
	end
end
