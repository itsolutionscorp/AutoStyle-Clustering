class Hamming
  def self.compute(x, y)
    x.chars.zip(y.chars).count{ |a| a[0] != a[1] }
  end
end
