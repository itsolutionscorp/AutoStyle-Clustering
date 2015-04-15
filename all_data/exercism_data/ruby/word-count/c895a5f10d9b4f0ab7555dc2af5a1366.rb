class Phrase
	def initialize(sentence)
		@words = sentence.downcase.scan(/[a-z [0-9]]/).join.split(" ")
	end

	def word_count
		@words.inject({}) do |counter, word|
			counter[word] ||= 0
			counter[word] += 1
			counter
		end
	end
end
