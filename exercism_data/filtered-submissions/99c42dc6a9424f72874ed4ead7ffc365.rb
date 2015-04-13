def compute(strand1, strand2)
		short_strand = ""
		long_strand = ""
		hamming_distance = 0


    if strand1 == strand2
    	return hamming_distance

		elsif strand1.length <= strand2.length
      short_strand = strand1
      long_strand = strand2
    else
    	short_strand = strand2
    	long_strand = strand1
		end


		for i in 0..(short_strand.length - 1)
			hamming_distance += 1 if short_strand[i] != long_strand[i]
		end

		return hamming_distance
	end