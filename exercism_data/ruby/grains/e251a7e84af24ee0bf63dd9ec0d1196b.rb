#!/usr/bin/env ruby

class Grains
  def initialize(squares = 64)
    @squares = squares
  end

  def square(sq)
    1 << (sq - 1)
  end

  def total
    ~(~0 << @squares)
  end
end
