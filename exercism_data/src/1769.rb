def compute(strand_a, strand_b)
    distance = 0
    length = [strand_a.length, strand_b.length].min

    0.upto(length - 1) do |i|
      if strand_a[i] != strand_b[i]
        distance += 1
      end
    end
    distance
  end