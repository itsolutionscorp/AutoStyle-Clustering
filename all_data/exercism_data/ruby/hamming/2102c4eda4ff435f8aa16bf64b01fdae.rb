class Hamming
  def self.compute(a, b)
    a.size.times.count{ |i| b[i] && a[i] != b[i] }
  end
end
