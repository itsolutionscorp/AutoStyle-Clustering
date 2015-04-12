class Hamming

  def compute(a, b)
    [a, b].size.times.count { |i| a[i] != b[i] }
  end

end
