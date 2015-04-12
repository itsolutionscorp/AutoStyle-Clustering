class Phrase 

	def initialize(string)
		@string = string.downcase
	end

	def word_count
		@string.scan(/[\w\']+/).inject({}) do |count, word| 
			count[word] ? count[word] += 1 : count[word] = 1
			count
		end
	end
end
