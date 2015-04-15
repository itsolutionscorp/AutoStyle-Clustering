class Hamming
  def self.compute(a, b)
    (0...a.length).count { |i| a[i] != b[i] }
  end
end
