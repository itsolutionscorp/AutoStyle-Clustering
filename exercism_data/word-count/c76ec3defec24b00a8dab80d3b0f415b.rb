class Phrase
	attr_accessor :word_count;

	def initialize(phrase)
		@word_count = Hash.new
		@word_count.default = 0

		phrase.downcase.gsub(/[^a-z0-9'\s]/i, ' ').split(' ').each do |word|
			@word_count[word] += 1
		end
	end
end
