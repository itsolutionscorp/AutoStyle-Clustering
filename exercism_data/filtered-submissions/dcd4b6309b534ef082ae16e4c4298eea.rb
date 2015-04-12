def compute(dna_one, dna_two)
      (0...dna_one.length).count {|i| dna_one[i] != dna_two[i]}
    end