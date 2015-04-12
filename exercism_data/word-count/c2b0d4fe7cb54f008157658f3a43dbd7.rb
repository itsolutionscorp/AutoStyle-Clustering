class Phrase
	def initialize arg
		words = arg.scan(/('\w+)|(\w+'\w+)|(\w+')|(\w+)/).map! {|w| w }.to_a.flatten.compact.map! {|w| w.downcase}
		@hash = {}
		words.map! do |word|
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
