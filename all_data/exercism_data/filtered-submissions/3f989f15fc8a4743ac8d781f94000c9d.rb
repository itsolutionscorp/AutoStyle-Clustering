def compute(strand_a, strand_b)
    hamming_distance = 0
    [strand_a.size, strand_b.size].min.times do |n|
      hamming_distance += 1 unless strand_a[n] == strand_b[n]
    end
    hamming_distance
  end
end
Hamming.ext