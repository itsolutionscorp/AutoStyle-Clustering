module Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).map{|x, y| mutation?(x, y) ? 0 : 1}.reduce(0,:+)
  end

  def self.mutation?(x, y)
    x == y or x.nil? or y.nil?
  end
end
