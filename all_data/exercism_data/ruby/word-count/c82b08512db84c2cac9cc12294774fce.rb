class Phrase
	def initialize(sentence)
		@words = sentence.downcase.split(/\W+/)
	end

	def word_count
		@words.each_with_object(Hash.new(0)) do |word, hash|
			hash[word] += 1
		end
	end
end
