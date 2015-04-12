class Phrase
	def initialize(str)
		@words_arr = str.downcase.scan(/\w+/)
	end
	def word_count
		return @words_arr.each_with_object({}) { |word, h| h[word] = (h.has_key?(word) ? h[word] += 1 : h[word] = 1) }
    end
end
