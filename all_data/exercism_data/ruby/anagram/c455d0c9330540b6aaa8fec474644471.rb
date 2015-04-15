class Anagram
	attr_reader :matcher

	def initialize(matcher)
		@matcher = matcher
	end

	def match(list)
		list.select do |word|
			word_match?(word, matcher) && word.upcase != matcher.upcase
		end
	end

	def split_sort(word)
		word.upcase.chars.sort
	end

	def word_match?(word, matcher)
	 split_sort(word) == split_sort(matcher)
	end
end
