class Hamming
  def self.compute(x, y)
    x.split('').zip(y.split('')).count{ |a| a[0] != a[1] }
  end
end
