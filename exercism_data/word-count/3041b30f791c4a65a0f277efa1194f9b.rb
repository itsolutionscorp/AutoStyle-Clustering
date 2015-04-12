class Phrase
	attr_reader :word_count
	def initialize(words)
		@word_count = {}
		words = words.downcase.gsub(/[^0-9a-z\']/,' ')

		words.split(' ').each do |word|
			word_count[word] = word_count.key?(word) ? word_count[word] + 1 : 1
		end
	end
end
