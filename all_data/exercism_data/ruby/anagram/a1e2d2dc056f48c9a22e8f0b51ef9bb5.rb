class Anagram
	def initialize(word)
		@word = word.downcase
	end
	
	def match(input)
		input.find_all do |word|
			is_anagram? word
		end
	end
	
	private
	
	def unfold(input)
		input.split('').sort
	end
	
	def is_anagram?(input)
		input = input.downcase
		unfold(@word) == unfold(input) && @word != input
	end
end
