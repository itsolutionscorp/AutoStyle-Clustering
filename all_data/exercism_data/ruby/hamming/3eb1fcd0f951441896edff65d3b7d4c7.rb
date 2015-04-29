class Hamming
  def self.compute(a, b)
    (0...length(a, b)).count { |i| a[i] != b[i] }
  end

  def self.length(a, b)
    [a.length, b.length].min
  end
end
