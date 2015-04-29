class Phrase
attr_reader :word
	def initialize(word)
		@word = word
	end

	def equalize
		word.downcase.scan(/[\w']+/)
	end

	def word_count
		equalize.inject(Hash.new(0)) { |result, ele| result[ele] +=1; result } 
	end
end
