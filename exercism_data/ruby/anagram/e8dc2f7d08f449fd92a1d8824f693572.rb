require 'pry'

class Anagram
	attr_reader :originalword, :originalword_letters, :originalword_letter_count
	
	def initialize(originalword)
		@originalword = originalword
	end

	def match(wordsarray)
		wordsarray = wordsarray.select do |word|
			anagrams?(word,originalword)
		end
		wordsarray = wordsarray.uniq
	end
	
	private

	def anagrams?(word1,word2)
		word1 = word1.downcase
		word2 = word2.downcase
		return false if word1 == word2
		count_letters(word1) == count_letters(word2)
	end

	def count_letters(word)
		letters = extract_letters(word)
		count = Hash.new(0)
		letters.each do |letter|
			count[letter] += 1
		end
		count
	end

	def extract_letters(word)
		word.downcase.split('')
	end
end
