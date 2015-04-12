class Phrase
	def initialize arg
		@hash = {}
		words = arg.downcase.scan(/('\w+)|(\w+'\w+)|(\w+')|(\w+)/).to_a.flatten.compact.map! do |word|
			unless @hash.has_key?(word)
				@hash[word] = 1
			else
				@hash[word] += 1
			end
		end
	end

	def word_count
		@hash
	end
end
