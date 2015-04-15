class Anagram
	
	attr_reader :word

	def initialize(word)
		@word = word
	end

	def match(list)
		list.select do |w|
			 !same_word?(word, w) && same_letters?(word, w)
		end
	end

	private

		def same_word?(a, b)
			a.casecmp(b) == 0
		end

		def same_letters?(a, b)
			return false if a.length != b.length

			a.downcase.chars.sort == b.downcase.chars.sort
		end

end
