#!/usr/bin/env ruby
class Hamming 
	def self.compute(dna_one, dna_two)
		count = 0
		dna_one.length.times do |x|
			count += 1 if (dna_one[x] != dna_two[x]) && dna_two[x]
		end
		count
	end
end
