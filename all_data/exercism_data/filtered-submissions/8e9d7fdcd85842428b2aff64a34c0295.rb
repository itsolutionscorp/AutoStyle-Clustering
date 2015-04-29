def compute(dna1, dna2)
    return 0 if dna1.nil? || dna2.nil?
    diffs=0
    smaller_size = dna1.size < dna2.size ? dna1.size : dna2.size
    (0..(smaller_size-1)).each {|i| diffs += 1 if dna1[i] != dna2[i] }

    return diffs
  end