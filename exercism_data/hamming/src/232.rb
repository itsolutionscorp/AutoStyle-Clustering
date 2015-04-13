def compute(dna1, dna2)

    test_length = [dna1.length, dna2.length].min

    differences = 0


    for i in 0..(test_length-1)
      if dna1[i] != dna2[i]
        differences += 1
      end
    end

    return differences
  end