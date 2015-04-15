class Phrase

	def initialize(words)
		@words = words.downcase.gsub(/[^0-9A-Za-z ,']/, '').scan(/[A-Za-z'0-9]+/)
	end

	def word_count
		@words.reduce({}) {|cnt, w| cnt[w] ? cnt[w] += 1 : cnt[w] = 1; cnt }
	end

end
