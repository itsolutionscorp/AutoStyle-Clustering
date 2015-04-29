def compute(dna1, dna2)
		dna1.split("").each_with_index.map.select {|d, index|
			d != dna2[index]
		}.size
	end