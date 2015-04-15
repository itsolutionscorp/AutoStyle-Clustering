def compute(first_strand, second_strand)
    [first_strand.length, second_strand.length].min.times.count { | i |  first_strand[i] != second_strand[i] }
  end