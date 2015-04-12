def compute( dna1, dna2 )
		difference = 0
		dna1.each_char.with_index do |item, index|
			if item != dna2[index]
				difference = difference+1
			end
		end
		difference
	end