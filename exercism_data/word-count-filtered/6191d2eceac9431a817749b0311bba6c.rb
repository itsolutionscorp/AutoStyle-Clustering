class Phrase

	def initialize(phrase)
		@phrase = phrase.downcase
	end

	def word_count
		word_array = @phrase.gsub(/[^\w|']/, " ").split(" ")
		word_hash = {}

		word_array.each do |word|
			word_in_hash = word_hash[word]
			if word_in_hash
				word_hash[word] = word_in_hash + 1
			else
				word_hash[word] = 1
			end
		end
		
		word_hash
	end
end
