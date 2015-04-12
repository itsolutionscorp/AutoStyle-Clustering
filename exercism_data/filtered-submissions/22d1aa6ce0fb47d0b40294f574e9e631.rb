def compute(strandA, strandB)
    strandA.chars.zip(strandB.chars).count do |(a, b)|
      a && b && a != b
    end
  end