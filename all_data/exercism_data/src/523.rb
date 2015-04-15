def compute(strand_one, strand_two)

    strands = strand_one.bytes.zip(strand_two.bytes)


    strands.map{ |a, b| (a || b) ^ (b || a) > 0 ? 1 : 0 }.inject(:+)
  end