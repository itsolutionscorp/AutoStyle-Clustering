class Hamming
  def self.compute(a, b)
    (0...[a.size, b.size].min)
      .count { |i| a[i] != b[i] }
  end
end
