class Scrabble
	attr_reader  :input
	SCORES_HASH = {'A' => 1,'B' => 3, 'C' => 3, 'D' => 2, 'E' => 1,
										'F' => 4,'G' => 2, 'H' => 4, 'I' => 1, 'J' => 8,
										'K' => 5,'L' => 1, 'M' => 3, 'N' => 1, 'O' => 1,
										'P' => 3,'Q' => 10, 'R' => 1, 'S' => 1, 'T' => 1,
										'U' => 1,'V' => 4, 'W' => 4, 'X' => 8, 'Y' => 4,
										'Z' => 10
									}
	def initialize(input)
		@input = input
	end
	def score
		score = 0		
		unless (input.nil? || input.strip() == "")
			string = input.upcase().split('')
			string.each do |s|
				score += SCORES_HASH[s]
			end
		end
		return score
	end

	def self.score(input)
		score = 0		
		unless (input.nil? || input.strip() == "")
			string = input.upcase().split('')
			string.each do |s|
				score += SCORES_HASH[s]
			end
		end
		return score
	end

end
