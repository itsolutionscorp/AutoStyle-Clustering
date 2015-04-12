def compute(strand1, strand2)
    strands = strand1.chars.zip(strand2.chars)
    strands.count do |n1, n2|
      n1 && n2 && n1 != n2
    end
  end