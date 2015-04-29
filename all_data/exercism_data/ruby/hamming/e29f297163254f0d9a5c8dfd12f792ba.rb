class Hamming
  def self.compute(strand_a, strand_b)
    min_length = [strand_a.size, strand_b.size].min
    min_length.times.count { |index| strand_a[index] != strand_b[index] }
  end
end
