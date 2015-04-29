class Anagram

	def initialize(word)
		@original_word = word.to_s.downcase
		@word = word.to_s.downcase.chars.sort.join
	end

	def match(list)
		anagrams = Array.new

		list.each do |check|
			if is_anagram?(check) && check.downcase != @original_word
				anagrams << check
			end
		end

		return anagrams
	end

	def is_anagram?(check)
		if @word == check.to_s.downcase.chars.sort.join
			return true
		else
			return false
		end
	end

end
