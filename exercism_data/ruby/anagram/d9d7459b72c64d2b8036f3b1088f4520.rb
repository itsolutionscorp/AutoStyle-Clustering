class Anagram
	
	attr_reader :word

	def initialize(word)
		@word = word
	end

	def match(list)
		list.select do |w|
			 anagram?(word, w)
		end
	end

	private

		def anagram?(a, b)
			!same_word?(a, b) && same_letters?(a, b)
		end

		def same_word?(a, b)
			a.casecmp(b) == 0
		end

		def same_letters?(a, b)
			return false if a.length != b.length

			a.downcase.chars.sort == b.downcase.chars.sort
		end

end
