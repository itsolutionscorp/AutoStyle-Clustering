def compute(dna1,dna2)
	[dna1.length, dna2.length].min.times
                                  .zip(dna1.each_char, dna2.each_char)
                                  .count { |arr| arr[1]!=arr[2] }
    end