class Phrase
	def initialize(phrase)
		@words = phrase.gsub(/[^A-Za-z0-9\s]/, ' ').split
	end

	def word_count
		@words.reduce({}) do |memo, word|
			key = word.downcase
			memo[key] = memo[key] || 0
			memo[key] += 1
			memo
		end
	end
end
