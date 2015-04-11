class Hamming
  def self.compute(a, b)
    a = a.split("")
    b = b.split("")
    a.zip(b).select {|x|
      x[0] != x[1] and x.compact! == nil
    }.length
  end
end
