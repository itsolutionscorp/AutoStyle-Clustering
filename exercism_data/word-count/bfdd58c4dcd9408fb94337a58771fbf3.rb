class Phrase

attr_reader :sentence
	def initialize(sentence)
		@sentence = sentence.downcase.split(/[^\w']+/)
	end

	def word_count
		words = Hash.new
		@sentence.each do |word|
			if words.has_key?(word)
				words[word] += 1
			else
				words[word] = 1
			end
		end
		words
	end

end
