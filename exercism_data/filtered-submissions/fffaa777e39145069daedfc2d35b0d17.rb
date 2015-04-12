class Hamming
  def compute(strand_a, strand_b)
    min_size = [strand_a.size, strand_b.size].min
    counter = 0
    i = 0
    min_size.times do
      counter += 1 unless strand_a[i] == strand_b[i]
      i += 1
    end
    counter
  end
end
