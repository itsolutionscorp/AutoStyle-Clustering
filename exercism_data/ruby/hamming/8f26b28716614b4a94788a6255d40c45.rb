class Hamming

  def self.compute(a, b)
    length = set_length(a, b)
    length.times.count { |i| a[i] != b[i] }
  end

  def self.set_length(a, b)
    [a, b].min.length
  end
end
