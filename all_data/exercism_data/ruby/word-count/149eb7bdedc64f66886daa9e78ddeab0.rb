class Phrase
	def initialize(a_phrase)
		@a_phrase = a_phrase
	end

	def word_count
		word_hash = Hash.new(0)
		words = @a_phrase.split(" ")
		words.each do |w| 
			word_hash[w] += 1
		end
		word_hash
	end
end
