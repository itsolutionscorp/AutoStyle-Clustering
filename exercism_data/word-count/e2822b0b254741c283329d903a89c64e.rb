class Phrase

	def initialize(words)
		@words = words.gsub(/[^\w\s]+/, ' ').downcase.split
		@sums = {}
		add_up_words
	end

	def word_count
		@sums
	end

	private

	def add_up_words
		@words.each { |word| @sums[word] ? @sums[word] += 1 : @sums[word] = 1 }
	end
end
