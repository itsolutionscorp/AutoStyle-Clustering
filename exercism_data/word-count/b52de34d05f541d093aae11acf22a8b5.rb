class Phrase
	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		phrasearray = @phrase.scan(/[\w']+/)
		@count = Hash.new(0)
		phrasearray.each do |word|
			@count[word.downcase] += 1
		end
		@count
	end
end
