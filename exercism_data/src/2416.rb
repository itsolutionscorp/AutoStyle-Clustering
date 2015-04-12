def compute(strand_A, strand_B)
    common_length = [strand_A.length, strand_B.length].min
    hamming_distance = 0
    common_length.times do |index|
      hamming_distance += 1 if strand_A[index] != strand_B[index]
    end
    hamming_distance
  end