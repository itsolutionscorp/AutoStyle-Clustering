class Phrase
	attr_reader :word_count
	attr_reader :phrase

	def initialize phrase
		@phrase = phrase
	end

	def word_count
		@word_count ||= count_words
	end

	private

	def count_words
		@word_count = {}
		words = @phrase.split(",").to_s.split(" ")
		words.collect do |word|
			word = word.gsub(/[^0-9A-Za-z]/, '').downcase
			@word_count.store(word, number_of_words(word)) unless word.empty?
		end
		@word_count
	end

	def number_of_words word
		@word_count[word].nil? ? 1 : @word_count[word]+1
	end

end
