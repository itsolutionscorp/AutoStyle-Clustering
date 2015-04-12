class Phrase
	def initialize(sentence)
		@words = sentence.downcase.split(/[^a-zA-Z0-9]/)
	end

	def word_count
		word_count = Hash.new(0)
		@words.each do |word|
			word_count[word] += 1 unless word.empty?
		end
		word_count
	end
end
