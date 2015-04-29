class Anagram
	def initialize(first_word)
		@word = sorted_letters_of(first_word)
	end

	def match(possible_anagrams)
		possible_anagrams.each_with_object([]) do |random_word, anagrams|
			anagram = sorted_letters_of(random_word)
			if @word == anagram
				anagrams << random_word
			end
		end
	end

	def sorted_letters_of(word)
		word.chars.sort.join
	end
end
