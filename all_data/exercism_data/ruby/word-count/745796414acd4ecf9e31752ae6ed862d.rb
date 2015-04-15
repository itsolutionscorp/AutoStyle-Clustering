class Phrase
	def initialize(phrase)
		@content = phrase
	end

	def word_count
		results = {}
		@content.split(/[,\s]/).each do |word|
			word = word.downcase.gsub(/[^0-9a-z \']/, '')
			if word != ""
				if results.include?(word)
					results[word] += 1
				else
					results[word] = 1
				end
			end
		end
		results
	end
end
