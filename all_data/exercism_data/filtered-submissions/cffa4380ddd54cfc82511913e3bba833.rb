def compute(strand_a, strand_b)

    if (strand_a.length != strand_b.length)
      return nil
    end

    hamming_difference = 0

    strand_a.length.times do |idx|
      if strand_a[idx] != strand_b[idx]
        hamming_difference += 1
      end
    end
    return hamming_difference
  end