class Hamming
  def self.compute(x, y)
    [x,y].map(&:size).min.times.count { |i| x[i] != y[i] }
  end
end
