def compute(dna1, dna2)
    count = 0
    x = dna1.length - 1

    while x >= 0
      dna1[x] == dna2[x] ? count : count+=1
    x = x - 1
    end
    assert_equal = count
  end