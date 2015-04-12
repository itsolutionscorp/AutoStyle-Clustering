class Hamming

  def compute(x, y)
    [x, y].min.length.times.count { |i| x[i] != y[i] }
  end # end compute

end # end Hamming
