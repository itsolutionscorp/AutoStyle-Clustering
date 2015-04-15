require 'prime'

DIGIT_OUTPUT = {
	3 => 'Pling',
	5 => 'Plang',
	7 => 'Plong'
}

class Raindrops
	def self.convert(digit)
		return digit.to_s if digit == 1
		raindrop = ''
		digit.prime_division.transpose[0].map { |d|	raindrop << DIGIT_OUTPUT.fetch(d, '') }
		raindrop.empty? ? digit.to_s : raindrop
	end
end
