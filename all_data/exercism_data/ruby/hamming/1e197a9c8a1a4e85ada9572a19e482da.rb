class Hamming
  def self.compute(a,b)
    [a.chars.length, b.chars.length].min.times.count{ |i|  a[i] != b[i] }
  end
end
