class Phrase
	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		@phrase.downcase.scan(/[\w']+/).reduce({}) do |acc, x| 
			acc[x] = acc.fetch(x, 0) + 1
			acc
		end
	end
end
