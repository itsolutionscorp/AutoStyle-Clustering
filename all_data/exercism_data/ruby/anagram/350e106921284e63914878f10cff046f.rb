class Anagram
	def initialize(word)
		@word = word
	end
	def match(words)
		words.select do |word|
			word.chars.sort == letters(@word)
		end
	end
	def letters(word)
		word.chars.sort
	end
end
