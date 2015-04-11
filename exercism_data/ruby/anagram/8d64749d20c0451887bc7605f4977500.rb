class Anagram
	
	attr_reader :word, :letterCount

	def initialize(word)
		@word = word
		@letterCount = countLetters(word)
	end

	def match(list)
		list.select do |other|
			 anagram?(other)
		end
	end

	private

		def anagram?(other)
			!same_word?(other) && same_letters?(other)
		end

		def same_word?(other)
			word.casecmp(other) == 0
		end

		def same_letters?(other)
			return false if word.length != other.length
			
			letterCount == countLetters(other)
		end

		def countLetters(word)
			word.downcase.chars.each_with_object(Hash.new(0)) do |letter, hash|
				hash[letter] += 1
			end
		end

end
