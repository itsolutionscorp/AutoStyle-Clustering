class Hamming

  def self.compute(a, b)
    length = common_length(a, b)
    length.times.count { |i| a[i] != b[i] }
  end

  def self.common_length(a, b)
    [a, b].min.length
  end
end
