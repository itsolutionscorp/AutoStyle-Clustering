class Anagram
	def initialize(word)
		@word = word
		@sortedword = sort_chars(word)
	end

	def match(list)
		temp = []
		for item in list
			temp.push item if is_anagram? item
		end
		temp
	end

	private

	def is_anagram?(item)
		sort_chars(item) == @sortedword && item.casecmp(@word) != 0
	end

	def sort_chars(word)
		word.downcase.chars.sort.join
	end
end
