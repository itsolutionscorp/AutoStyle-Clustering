class Phrase
	attr_accessor :word_count

	def initialize(phrase)
		words = phrase.downcase.scan(/[\w\']+/)
		@word_count = words.each_with_object(Hash.new(0)) do |word, word_count|
			word_count[word] += 1
		end
	end
end
