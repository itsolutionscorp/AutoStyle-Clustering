module Hamming
  def self.compute(a, b)
    a.split('').zip(b.split('')).inject(0) { |n, (x,y)| x == y ? n : n + 1 }
  end
end
