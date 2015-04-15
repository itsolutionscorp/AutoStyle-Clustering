class Hamming

  def self.compute(a, b)
    a.size.times.count { |n| a[n] != b[n] }
  end

end
