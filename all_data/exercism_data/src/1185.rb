def compute(strand_1, strand_2)
    hamming_difference = 0
    length = [strand_1.length, strand_2.length].min
    length.times { |i| hamming_difference += 1 if strand_1[i] != strand_2[i] }
    hamming_difference
  end