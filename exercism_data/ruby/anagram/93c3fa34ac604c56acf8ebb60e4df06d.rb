require 'pry'

class Anagram
	def initialize(originalword)
		@originalword = originalword
		@originalword_letters = extract_letters(@originalword)
		@originalword_letter_count = count_letters(@originalword)
	end

	def match(wordsarray)
		wordsarray = wordsarray.select do |word|
			word = word.downcase
			next if word == @originalword
			@originalword_letter_count == count_letters(word)

		end
		wordsarray = wordsarray.uniq
	end
	
	private

	def extract_letters(word)
		word.downcase.split('')
	end

	def count_letters(word)
		letters = extract_letters(word)
		@count = Hash.new(0)
		letters.each do |letter|
			@count[letter] += 1
		end
		@count
	end
end
