module Hamming
    def compute(dna1,dna2)
	[dna1.length, dna2.length].min.times # Compare from both strings
                                  .zip(dna1.each_char, dna2.each_char) # Join to [i,c1,c2]
                                  .count { |arr| arr[1]!=arr[2] } # Count when c1 != c2
    end
end
