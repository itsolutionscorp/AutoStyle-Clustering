def compute(first_strand, second_strand)
    hamming_pt = 0
    i = 0
    min_length = [first_strand.length, second_strand.length].min
    while i < min_length
      if first_strand[i] != second_strand[i]
        hamming_pt += 1
      end
      i += 1
    end
    return hamming_pt
  end