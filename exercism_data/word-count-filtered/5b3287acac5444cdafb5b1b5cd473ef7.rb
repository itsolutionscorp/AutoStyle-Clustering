class Phrase
	def initialize(sentence)
		@sentence = sentence.gsub(',', ' ').gsub(':', ' ')
		.gsub('!!&@$%^&', '').downcase
	end

	def word_count
		words = @sentence.split
		word_occurrencies = Hash.new(0)
		words.each do |word|
			word_occurrencies[word] += 1
		end
		word_occurrencies
	end
end
