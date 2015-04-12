class Hamming
  def compute(x, y)
    x.split('').zip(y.split('')).count { |a, b| a != b }
  end
end
