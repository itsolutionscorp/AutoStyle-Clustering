module Hamming
  def self.compute(a, b)
    compare_strings(a.chars, b.chars)
      .reduce(0,:+)
  end

  def self.mutation?(x, y)
    x != y and not x.nil? and not y.nil?
  end

  def self.compare_strings(xs, ys)
    xs.zip(ys).map{|x,y| mutation?(x,y) ? 1 : 0}
  end
end
