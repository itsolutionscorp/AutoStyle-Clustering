def compute(strand_a, strand_b)
    hamming_distance = 0

    strand_1 = strand_a.chars
    strand_2 = strand_b.chars

    strand_1.zip(strand_2) { |a, b|
      if a != b && b != nil
        hamming_distance += 1
      end
    }

    hamming_distance

  end