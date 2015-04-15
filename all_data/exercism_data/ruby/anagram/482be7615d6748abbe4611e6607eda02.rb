class Anagram
	def initialize(first_word)
		@word = sort_word(first_word)
	end

	def match(list_of_anagrams)
		list_of_anagrams.each_with_object([]) do |list, anagrams|
			anagram = sort_word(list)
			if @word == anagram
				anagrams << list
			end
		end
	end

	def sort_word(word)
		word.chars.sort.join
	end
end
