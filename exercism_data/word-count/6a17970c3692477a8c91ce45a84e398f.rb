class Phrase
	def initialize(phrase)
		@the_phrase = phrase
	end

	def word_count
		word_count = Hash.new(0) #default new keys to 0 value
		#split the phrase by non word characters then increment each word in word_count.
		#downcase is used to normalize capitalization
		@the_phrase.split(/\W+/).each{ |word| word_count[word.downcase] += 1 }
		return word_count
	end
end
