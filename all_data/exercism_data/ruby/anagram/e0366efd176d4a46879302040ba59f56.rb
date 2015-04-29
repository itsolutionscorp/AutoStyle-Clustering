class Anagram
	
	def initialize(word)
		@word = word
	end

	def match(list)
		list.select do |w|
			 !same_word?(@word, w) && same_letters?(@word, w)
		end
	end

	private

		def same_word?(a, b)
			a.casecmp(b) == 0
		end

		def same_letters?(a, b)
			return false if a.length != b.length

			aCounts, bCounts = [a.downcase, b.downcase].map do |word|
				countLetters(word)
			end

			aCounts == bCounts
		end

		def countLetters(word)
			word.chars.each_with_object(Hash.new(0)) do |letter, hash|
				hash[letter] += 1
			end
		end

end
