class Hamming

  def self.compute(x, y)
    [x.length, y.length].min.times.count { |i| x[i] != y[i] }
  end # end compute

end # end Hamming
