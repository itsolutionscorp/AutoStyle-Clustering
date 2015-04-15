class Hamming
  def self.compute(a, b)
    a.length.times.reduce(0) { |m,i| (a[i] == b[i] && m) || m+1 }
  end
end
