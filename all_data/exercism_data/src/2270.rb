def compute(strandA, strandB)
    strandANucleids = strandA.chars
    strandBNucleids = strandB.chars
    diff = strandANucleids.zip(strandBNucleids).select do |(a, b)|
      a and b and a != b
    end
    diff.length
  end