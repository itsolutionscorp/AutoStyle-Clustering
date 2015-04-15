class Anagram
	def initialize (word)
		@word= word.split("").sort
	end

	def match(words)
		words.select do |word|
			word.split("").sort== @word
		end
	end
end
	
