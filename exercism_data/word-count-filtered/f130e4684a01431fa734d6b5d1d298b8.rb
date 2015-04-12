require 'set'

class Phrase
	
	IGNORE = /[.:!@$%^&]/
	REPLACE = /\,/
	SPACE = ' '
	EMPTY = ''
	
	def initialize(phrase)
		@words = phrase.
			 strip.
			 gsub(IGNORE, EMPTY).
			 gsub(REPLACE, SPACE).
			 downcase.
			 split
		@word_set = Set.new @words
	end

	def word_count
		counted = []
		@word_set.each do |word|
			counted << @words.count(word)
		end
		Hash[@word_set.zip counted]
	end
end
