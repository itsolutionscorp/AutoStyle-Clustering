module Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).map{|x, y| mutation?(x, y) ? 1 : 0}.reduce(0,:+)
  end

  def self.mutation?(x, y)
    x != y and not x.nil? and not y.nil?
  end
end
