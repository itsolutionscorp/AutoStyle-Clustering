def compute (dna1, dna2)
    # if dna strings have different length, end
    if dna1.length != dna2.length
      raise "strands need to be the same length!"
    end

    dist = 0

    i = 0
    while i < dna1.length
      if dna1[i] != dna2[i]
        dist += 1
      end
      i += 1
    end

    return dist

  end