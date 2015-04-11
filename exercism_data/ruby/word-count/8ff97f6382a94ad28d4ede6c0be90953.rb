class Phrase
	def initialize(phrase)
		@phrase = phrase.downcase.delete("^a-z 0-9").split(" ")
	end

	def word_count
		word_value = {}
		@phrase.each do |word|
			if !word_value[word].nil?
				word_value[word] += 1
			else
				word_value[word] = 1
		    end
		end
		word_value
	end

end
