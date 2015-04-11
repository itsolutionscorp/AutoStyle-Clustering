class Phrase

	def initialize(words)
		@words = words.gsub(/[!&@$%^:;,.]/, ' ').downcase.split
		@sums = {}
		add_to_hash
	end

	def word_count
		@sums
	end

	private

	def add_to_hash
		@words.each { |word| @sums[word] ? @sums[word] += 1 : @sums[word] = 1 }
	end

end
