def compute(strandA, strandB)
    i, difference = 0, 0

    while i < strandA.length && i < strandB.length
      difference += 1 if strandA[i] != strandB[i]
      i += 1
    end

    difference
  end