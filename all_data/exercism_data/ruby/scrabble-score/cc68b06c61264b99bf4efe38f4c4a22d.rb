POINTS = 
{
	'A' => 1, 'E' => 1, 'I' => 1, 'O' => 1, 'U' => 1, 'L' => 1, 'N' => 1, 'R' => 1, 'S' => 1, 'T' => 1,
	'D' => 2, 'G' => 2,
	'B' => 3, 'C' => 3, 'M' => 3, 'P' => 3,
	'F' => 4, 'H' => 4, 'V' => 4, 'W' => 4, 'Y' => 4,
	'K' => 5,
	'J' => 6, 'X' => 6,
	'Q' => 10, 'Z' => 10,
}

class Scrabble
	def initialize(the_word = '')
		@word = (the_word) ? the_word : ''
	end

	def score()
		self.class.score(@word)
	end

	def self.score(the_word)
		the_word.gsub(/[^[:alpha:]]/, '').upcase.chars.inject(0){|sum, c| sum += POINTS[c]}
	end
end
