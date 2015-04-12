class Phrase

	def initialize(phrase)
		@words = phrase.split
	end

	def wor_count
		words = {}

		@words.each do |word|
			words[word] = 1
		end

		words
	end
end



#	def initialize(phrase)
#		@phrase = phrase
#	end
#
#	def word_count
#		if @phrase == ""?
#			string.scan(/(\w|-)+/).size
#		end
#	end
