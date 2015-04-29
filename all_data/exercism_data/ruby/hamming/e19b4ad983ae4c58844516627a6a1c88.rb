class Hamming
  def self.compute(a, b)
    length = [a.size, b.size].min
    (0...length).count { |i| a[i] != b[i] }
  end
end
