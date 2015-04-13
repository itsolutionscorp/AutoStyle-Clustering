def compute(dna1, dna2)
		distance = 0
		maximum = dna1.length


		if( dna1.length > dna2.length )
			maximum = dna2.length
		end


		maximum.times do |i|
			if (dna1[i] != dna2[i])
				distance += 1
			end
		end

		return distance
	end