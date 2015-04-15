class Phrase
	attr_accessor :word_count

	def initialize(phrase)
		@word_count = {}
		words = phrase.downcase.gsub(/[:!&@$%^&.]/, '').gsub(/,/, ' ').split(' ')
		words.each do |word|
			if word_count[word]
				word_count[word] += 1
			else
				word_count[word] = 1
			end
		end
	end
end
