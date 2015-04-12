class Phrase

	def initialize(words)
		@words = words.downcase.scan(/\w+/)
		@sums = Hash.new(0)
		add_up_words
	end

	def word_count
		@sums
	end

	private

	def add_up_words
		@words.each { |word| @sums[word] += 1 }
	end
end
