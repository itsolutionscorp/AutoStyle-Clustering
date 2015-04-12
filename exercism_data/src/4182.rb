def compute(first_strand, second_strand)
    hamming_distance = 0
		first_dna  = first_strand.chars
    second_dna = second_strand.chars
    first_dna.each_with_index do | element ,x |
      if first_dna[x] != second_dna[x]
        hamming_distance += 1
      end
    end
    hamming_distance
	end
end

if __FILE__ == $0 
puts Hamming.compute("GAGCCTACTAACGGGAT", "CATCGTAATGACGGCCT")