class Anagram

	def initialize(word)
		#detector = s.split('').shuffle(s.length()).join
		@word = word
	end

	def match(words)
		words.select do |word|
			word.split("").sort == @word.split("").sort
		end
end
