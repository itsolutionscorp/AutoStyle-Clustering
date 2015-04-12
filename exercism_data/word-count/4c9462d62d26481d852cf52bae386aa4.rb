class Phrase
	def initialize(phrase)
		@phrase = phrase 
	end

	def word_count
		@word_count ||= count_words
	end

private

	attr_reader :phrase

	def normalized_text
		phrase.downcase
	end

	def count_words
		count = Hash.new(0)

		normalized_text.scan(/[[:alnum:]]+/) do |word|
			count[word] += 1
		end

		count
	end
end
