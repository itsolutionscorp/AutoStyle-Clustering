class Phrase
	def initialize(phrase)
		@words = phrase.downcase.gsub(/[^\w']/, " ").split
	end
	
	def word_count
		Hash[@words.map{ |word| [word, @words.count(word)] }]
	end
end
