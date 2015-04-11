class Anagram

	def initialize(word)
		@word = word.downcase
		@hash = Hash.new(0)
		@word.split('').each {|letter| @hash[letter]+=1 }
	end

	def match(words)
		matches = []
		words.each do |word|
			hash = Hash.new(0)
			word.downcase.split('').each {|letter| hash[letter]+=1 }
			if hash == @hash and word.downcase != @word
				matches << word
			end
		end
		matches
	end


end
