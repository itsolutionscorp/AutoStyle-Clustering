def compute(dna1, dna2)
	
		count=0
		dna_short = dna1
		dna_short = dna2 if dna2.length < dna1.length

		for i in 0...dna_short.length
			count+=1 unless dna1[i] == dna2[i]
		end
		return count 
    end