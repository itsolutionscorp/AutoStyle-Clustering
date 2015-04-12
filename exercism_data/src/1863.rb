class Hamming
  def compute(a, b)
    [a, b].min.length.times.count { |i| a[i] != b[i] }
  end
end
