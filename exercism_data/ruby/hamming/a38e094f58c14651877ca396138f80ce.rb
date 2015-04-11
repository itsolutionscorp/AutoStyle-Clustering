class Hamming

  def self.compute(x, y)
    [x, y].min.length.times.count { |i| x[i] != y[i] }
  end # end compute

end # end Hamming
