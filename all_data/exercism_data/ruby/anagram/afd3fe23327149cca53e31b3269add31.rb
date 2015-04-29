class Anagram
	attr_accessor :word, :frequencies
	def initialize(word)
		@word = word.downcase
		@frequencies = char_frequency @word
	end

	def match(words)
		matches = []
		words.each do |word|
			next if word.downcase == @word
			matches << word if char_frequency(word.downcase) == @frequencies
		end
		matches
	end

	def char_frequency(word)
		hash = Hash.new(0)
		word.each_char do |char|
			hash[char] += 1
		end
		hash
	end
end
