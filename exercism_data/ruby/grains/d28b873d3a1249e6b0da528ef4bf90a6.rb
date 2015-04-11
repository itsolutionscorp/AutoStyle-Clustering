#!/usr/bin/env ruby
# encoding: utf-8
# Grains
class Grains
  # Calculate the sum of all grains in each square of the board
  def total
    sum = 0
    1.upto(64) { |i| sum += square(i) }
    sum
  end

  # We calculate the square of a number
  def square(cuadrado)
    2**(cuadrado - 1)
  end
end
