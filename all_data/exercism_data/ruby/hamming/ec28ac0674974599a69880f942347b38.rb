require 'byebug'

class Hamming
	def self.compute(strand_a, strand_b)
		hamming_distance = 0

		strand_a.each_char.with_index do|char, index|
			if char != strand_b[index] then
				hamming_distance += 1
			end
		end

		hamming_distance
	end
end
