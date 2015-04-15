class Hamming
  def self.compute(xs, ys)
    xs.chars.zip(ys.chars).count { |(x, y)| x != y }
  end
end
