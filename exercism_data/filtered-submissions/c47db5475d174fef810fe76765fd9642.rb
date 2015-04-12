def compute(dna1, dna2)
      [dna1.length, dna2.length].min.times.count { |i| dna1[i] != dna2[i] }
    end