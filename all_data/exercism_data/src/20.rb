def compute(dna_strand_1, dna_strand_2)
    i = 0
    hamming_distance = 0
    dna_strand_1.each_char do |x|
      hamming_distance += 1 if dna_strand_2.byteslice(i) != x && dna_strand_2.byteslice(i) != nil
      i += 1
    end
    hamming_distance
  end