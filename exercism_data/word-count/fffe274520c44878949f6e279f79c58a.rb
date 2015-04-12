class Phrase

	def initialize(sentence)
		@sentence = sentence.downcase.split(/[^\w']+/)
	end

	def word_count
		words = Hash.new(0)
		@sentence.each do |word|
			words[word] += 1
		end
		words
	end

end
