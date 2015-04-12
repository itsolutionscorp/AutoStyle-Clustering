def compute(dna_strand_a, dna_strand_b)
    hamming_distance = 0
    dna_strand_a.each_char.with_index do |base_pair_a, i|
      base_pair_b = dna_strand_b[i]
      hamming_distance += 1 if base_pair_b != base_pair_a && base_pair_b != nil
    end
    hamming_distance
  end