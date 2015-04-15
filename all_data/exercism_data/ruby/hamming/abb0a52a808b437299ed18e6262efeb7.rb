class Hamming
  def self.compute(a, b)
    min = [a.length, b.length].min
    (0...min).count { |i| a[i] != b[i] }
  end
end
