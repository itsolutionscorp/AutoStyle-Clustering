def compute(dna_one, dna_two)
		count = 0
		dna_one.length.times do |x|
			if (dna_one[x] != dna_two[x]) && dna_two[x]
				count += 1 
			end
		end
		count
	end