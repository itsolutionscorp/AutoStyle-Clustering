class Phrase
	def initialize str
		@phrase = str
	end

	def word_count
		words_seen = Hash.new(0)
		@phrase.scan(/\w+/).each {|word|
			words_seen[word.downcase]+=1
		}
		words_seen
	end
end
