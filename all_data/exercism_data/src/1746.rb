def compute(strand_a, strand_b)
    distance = 0
    length = [strand_a.length, strand_b.length].min
    length.times { |i| distance += 1 if strand_a[i] != strand_b[i] }
    distance
  end
end