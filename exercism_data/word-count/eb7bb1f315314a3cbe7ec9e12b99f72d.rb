class Phrase
	
	def initialize(string)
		@string = string.downcase
	end

	def	word_count
		words = Hash.new(0)
		@string.scan(/[\w']+/){|x| words[x] += 1}
		words
	end
end
