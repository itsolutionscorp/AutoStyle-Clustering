class Anagram
	def initialize (word)
		@word = word
		@word_letters = to_letters(word)
	end

	def match (list)
		list.select {|w| to_letters(w) == @word_letters && w.downcase != @word}
	end

	private
		def to_letters (word)
			word.downcase.chars.sort
		end
end
