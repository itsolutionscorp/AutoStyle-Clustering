class Hamming
  def compute(x, y)
    [x,y].map(&:size).min.times.count { |i| x[i] != y[i] }
  end
end
