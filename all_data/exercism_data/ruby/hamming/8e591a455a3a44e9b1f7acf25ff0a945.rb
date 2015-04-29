class Hamming
  def self.compute(a,b)
    limit = [a.size,b.size].min
    (0...limit).count { |i| a[i] != b[i] }
  end
end
