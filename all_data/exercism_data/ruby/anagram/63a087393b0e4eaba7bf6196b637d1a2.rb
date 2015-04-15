class Anagram
	def initialize( word )
		@word = word
		@characters = word.downcase.split('').sort
	end	

	def match( word_set )
		@matches = []
		word_set.each do |word|
			next if word.downcase == @word.downcase
			@matches << word if word.downcase.split('').sort == @characters
		end
		@matches
	end
end
