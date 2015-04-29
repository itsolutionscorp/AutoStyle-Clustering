#!/usr/bin/env ruby

class Grains
  def initialize(squares = 64)
    @squares = squares
  end

  def square(sq)
    2**(sq - 1)
  end

  def total
    2**@squares - 1
  end
end
