#!/usr/bin/env ruby
class Hamming 

	def compute(dna_one, dna_two)
		x = 0
		count = 0
		if dna_one.length < dna_two.length
			shortest_string = dna_one
			other_string = dna_two
		else
			shortest_string = dna_two
			other_string = dna_one
		end
		while x < shortest_string.length
			count += 1 if shortest_string[x] != other_string[x]
			x += 1	
		end
		return count
	end

end
