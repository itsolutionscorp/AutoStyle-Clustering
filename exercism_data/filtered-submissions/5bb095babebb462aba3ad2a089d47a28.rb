def compute(strand, other_strand)

    [strand.length, other_strand.length].min.times.count { |n| strand[n] != other_strand[n] }

  end