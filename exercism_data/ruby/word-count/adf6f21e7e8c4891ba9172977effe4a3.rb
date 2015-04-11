class Phrase
	def initialize(text)
		@text = text
		@map = Hash.new(0)
	end

	def word_count
		@count ||= create_word_count
	end

	private

	def create_word_count
		@text.scan(/['\w]+/).each do |word|
			@map[word.downcase] += 1
		end
		@map
	end
end

