class Phrase 

	def initialize(string)
		@string = string.downcase
	end

	def word_count
		@string.scan(/[\w\']+/).inject(Hash.new(0)) do |count, word| 
			count[word] += 1
			count
		end
	end
end
