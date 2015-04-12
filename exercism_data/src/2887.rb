class Hamming
  def compute(strand_a, strand_b)
    i = 0
    distance = 0
    while i < strand_a.length
      distance += 1 unless strand_a[i] == strand_b[i]
      i += 1
    end
    distance
  end
end
