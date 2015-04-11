class Scrabble
	def initialize(word)
		@word = word
	end

	def self.score(word)
		Scrabble.new(word).score
	end

	def score
		unless @word.kind_of? String
			return 0
		end

		@word.split("").inject(0) do |accumulator, character|
		 letter_score(character) + accumulator
		end
	end

	def letter_score(letter)
		if "AEIOULNRST".include? letter.upcase
			1
		elsif "DG".include? letter.upcase
			2
		elsif "BCMP".include? letter.upcase
			3
		elsif "FHVWY".include? letter.upcase
			4
		elsif "K" == letter.upcase
			5
		elsif "JX".include? letter.upcase
			8
		elsif "QZ".include? letter.upcase
			10
		else
			0
		end
	end
end
