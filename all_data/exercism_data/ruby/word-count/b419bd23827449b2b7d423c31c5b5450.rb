class Phrase

	def initialize(phrase)
		@words = phrase.split
	end

	def wor_count
		@words.each_with_object({}) do |word, words|
			words[word] ||= 0
			words[word] += 1
		end
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
