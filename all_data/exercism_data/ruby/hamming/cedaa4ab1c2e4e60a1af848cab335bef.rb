class Hamming
  def self.compute(x, y)
    if x !=y
      xs = x.split(//)
      ys = y.split(//)
      a = 0
      ys.each{
        |i|
        puts i == xs[a]
        a += 1
      }
      return a
    else
      return 0
    end
  end
end
