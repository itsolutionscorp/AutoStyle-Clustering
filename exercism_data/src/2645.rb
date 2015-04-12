class Hamming
  def compute(xs, ys)
    xs.chars.zip(ys.chars).count { |(x, y)| x != y }
  end
end
