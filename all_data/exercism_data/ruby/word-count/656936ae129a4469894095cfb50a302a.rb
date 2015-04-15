class Phrase
	def initialize(phrase)
		@words= phrase.downcase.gsub(/[^a-z\s0-9]/, '').split
	end

	def word_count
		@words.each_with_object({})do |word, word_count|
			word_count[word] ||=0
			word_count[word] +=1
		end
	end
end
