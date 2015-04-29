class Anagram

	def initialize(word)
		#detector = s.split('').shuffle(s.length()).join
		@word = word
	end

	def match(words)
		word.select do |word|
			word.split= @word

		end
	end

end
