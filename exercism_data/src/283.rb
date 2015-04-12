class Phrase
	def initialize(phrase)
		@words = phrase.scan(/\w+/)
	end

	def word_count
		@words.reduce({}) do |memo, word|
			key = word.downcase
			memo[key] = memo[key].to_i + 1
			memo
		end
	end
end
