class Scrabble

	attr_accessor :word

	@@LETTER_VALUES = {
		"aeioulnrst" 	=>  1,
		"dg" 			=>  2,	
		"bcmp"			=>  3,
		"fhvwy"			=>  4,
		"k"				=>  5,
		"jx"			=>  8,
		"qz"			=> 10
	}

	def initialize(word)
		@word = word ? scrub(word) : ''
	end

	def score
		@score ||= word.chars.map { |l| scoreLetter(l) }.reduce(0, :+)
	end

	def self.score(word)
		new(word).score
	end

	private

		def scrub(word)
			word.gsub(/\W/, '').downcase
		end

		def scoreLetter(letter)
			key, value = @@LETTER_VALUES.detect do |key|
				key.to_s =~ /#{letter}/
			end

			value
		end

end
