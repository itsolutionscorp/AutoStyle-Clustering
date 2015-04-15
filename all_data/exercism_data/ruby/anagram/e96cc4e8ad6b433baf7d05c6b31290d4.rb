class Anagram
	def initialize(word)
		@word = word
	end

	def match(possible_anagrams)
		possible_anagrams.inject([]) do |anagrams, possible_anagram|
			if letter_count_of(@word) == letter_count_of(possible_anagram)
				anagrams << possible_anagram
			end
			anagrams
		end
	end

	def letter_count_of(word)
		word.split("").inject({}) do |letter_count_hash, letter|
			letter_count_hash[letter] ||= 0
			letter_count_hash[letter] += 1
			letter_count_hash
		end
	end
end
