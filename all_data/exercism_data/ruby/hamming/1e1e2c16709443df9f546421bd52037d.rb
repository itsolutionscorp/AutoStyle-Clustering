class Hamming
  def self.compute strand_a, strand_b
    (0..strand_a.length - 1).count { |i|  strand_a[i] != strand_b[i] }
  end
end
