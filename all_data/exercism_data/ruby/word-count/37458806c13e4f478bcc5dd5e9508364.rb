class Phrase
	def initialize(phrase)
		@words= phrase.downcase.gsub(/[^a-z\s0-9]/, '').split
	end

	def word_count
		word_count= Hash.new(0)
		@words.each do |word|
			word_count[word] +=1
		end
		word_count
	end
end
