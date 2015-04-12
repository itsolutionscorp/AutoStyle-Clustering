class Hamming
  def compute(strand_a, strand_b)
    strand_a.size.times.count { |i| strand_a[i] != strand_b[i] }
  end
end
