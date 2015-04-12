class Phrase
	def initialize(phrase)
		@words = phrase.downcase.scan( /[\w ]/ ).join.split(" ")
	end

	def word_count
		@words.inject({}) do |count, word|
			count[word] ||= 0
			count[word] += 1
			count
		end
	end
end
