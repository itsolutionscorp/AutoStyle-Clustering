class Hamming

  def compute(a,b)
    min_length = [a.length, b.length].min
    min_length.times.count { |i| a[i] != b[i] }
  end

end
