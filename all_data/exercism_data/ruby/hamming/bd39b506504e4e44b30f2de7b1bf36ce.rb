class Hamming
  def self.compute(a, b)
    total = 0
    [a.size, b.size].min.times { |i| total += 1 if a[i] != b[i] }
    total
  end
end
