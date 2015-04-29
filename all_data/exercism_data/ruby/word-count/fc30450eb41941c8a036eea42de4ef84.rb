class Phrase
	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		@count = Hash.new(0)
		wordarray.each do |word|
			@count[word.downcase] += 1
		end
		@count
	end

	def wordarray
		@word_array = @phrase.scan(/[\w']+/)
		@word_array = @word_array.map {|w| w.downcase}
	end

end
