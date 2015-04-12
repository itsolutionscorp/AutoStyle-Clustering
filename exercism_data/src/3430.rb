def compute(x, y)
    [x.length, y.length].min.times.count { |i| x[i] != y[i] }
  end # end compute

end #