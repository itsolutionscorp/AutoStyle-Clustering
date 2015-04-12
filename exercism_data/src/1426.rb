def compute strand_A, strand_B
    hamming_count = 0

    test_length = [strand_A.length, strand_B.length].min

    test_length.times do |i|
      hamming_count += 1 if strand_A[i] != strand_B[i]
    end

    hamming_count
  end