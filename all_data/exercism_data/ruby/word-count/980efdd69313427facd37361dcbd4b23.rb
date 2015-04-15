class Phrase
	def initialize(sentence)
		@words = sentence.downcase.scan(/[a-z [0-9]]/).join.split(" ")
	end

	def word_count
		@counter = {}
		@words.each do |word|
			if @counter[word].nil?
				@counter[word] = 1
			else
				@counter[word] += 1
			end
		end
		return @counter
	end
end
