class Hamming
  def compute(x, y)
    x.chars.zip(y.chars).count do |a, b|
      a != b
    end
  end
end
