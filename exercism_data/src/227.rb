class Phrase
	PUNCTUATION = " :,.!^@$%&"
	
	def initialize(phrase)
		@phrase = phrase
	end

	def word_count()
		counts = Hash.new(0)
		@phrase.downcase.split(/[#{PUNCTUATION}]+/).each {|word|
			counts[word] += 1
		}
		return counts
	end
end
