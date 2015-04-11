class Hamming
  def self.compute(x, y)
    x.chars.zip(y.chars).count { |a,b| a != b }
  end
end
