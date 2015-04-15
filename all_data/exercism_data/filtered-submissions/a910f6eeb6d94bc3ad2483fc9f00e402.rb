def compute(dna_a,dna_b)
    min_length = [dna_a.length, dna_b.length].min
    differences = (0...min_length).count{|index| dna_a[index] != dna_b[index]}
  end