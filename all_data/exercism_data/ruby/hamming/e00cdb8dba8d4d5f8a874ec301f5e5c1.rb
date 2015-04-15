class Hamming
  def self.compute strand_a, strand_b
    return (0..strand_a.length).count { |i|  strand_a.chars[i] != strand_b.chars[i] }
  end
end
