class Phrase
	attr_reader :word_count

	def initialize(input)
		@word_count = {}
		extract_words(input).each { |w| update_word_count_with w }
	end
	
	def extract_words(input)
		input.scan(/[a-zA-Z0-9]+/)
	end
	
	def update_word_count_with(word)
		key = word.strip.downcase
		count = (@word_count.has_key? key) ? @word_count[key] : 0
		@word_count[key] = count + 1
	end
end
