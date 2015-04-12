require 'pp'
module Hamming
  def compute(as, bs)
    as.split('').zip(bs.split('')).inject(0) do |count, (a, b)|
      return count unless a && b
      count += a == b ? 0 : 1
    end
  end
end
