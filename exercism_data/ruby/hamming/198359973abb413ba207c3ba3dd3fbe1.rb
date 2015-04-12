class Hamming
  def self.compute(a, b)
    a.length.times.inject(0) { |total, i| a[i] == b[i] ? total : total + 1 }
  end
end