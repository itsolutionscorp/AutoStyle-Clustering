def compute(dna1, dna2)

		dna1 = dna1.split(//)
		dna2 = dna2.split(//)
		count = 0
		(0..dna1.length).to_a.each do |index|
			count += 1 if dna1[index] != dna2[index]
		end
		count
	end