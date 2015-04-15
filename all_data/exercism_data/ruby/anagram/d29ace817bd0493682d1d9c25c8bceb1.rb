class Anagram

	def initialize(word)
		@word = word
	end

	def match(array)
		answer = []
		array.each do |anagram|
			next if anagram.downcase == @word.downcase
			answer << anagram if anagram?(anagram, @word)
		end
		answer
	end

	private

	def anagram?(word1, word2)
		true if word1.anagram_test == word2.anagram_test
	end
	
end

class String

	def anagram_test
		self.downcase.split(//).sort
	end

end
