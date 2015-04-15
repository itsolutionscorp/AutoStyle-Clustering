#!/usr/bin/env ruby
class Hamming 

	def self.compute(dna_one, dna_two)
		x = 0
		count = 0
		dna_one.each_char do |letter|
			if (letter != dna_two[x]) && dna_two[x]
				count += 1 
			end
			x += 1
		end
		count
	end

end
