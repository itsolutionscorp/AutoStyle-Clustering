def compute(strand1, strand2)
    result = 0
    smallest_strand = (strand1.size > strand2.size) ? strand2.size : strand1.size
    smallest_strand.times { |i|
      result += 1 if strand1[i] != strand2[i]
    }
    result
  end