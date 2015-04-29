def compute(strand_a, strand_b)
    distance = 0

    [strand_a.length, strand_b.length].min.times do |index|
      distance += 1 if strand_a[index] != strand_b[index]
    end

    distance
  end