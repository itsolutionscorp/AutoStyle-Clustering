class Anagram
	def initialize(word)
		@word = word

	end

	def match(words)
		words.select do |word|
		analyze(word) == analyze(@word)
	end

	end
	def analyze(word)
		word.split("").sort
	end

end
