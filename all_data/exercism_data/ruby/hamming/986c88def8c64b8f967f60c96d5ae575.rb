#!/usr/bin/env ruby

class Hamming

  def self.compute(x, y)

    @limit = x.length < y.length ? x.length : y.length

    (0...@limit).count { |i| x[i] != y[i] }

  end

end
