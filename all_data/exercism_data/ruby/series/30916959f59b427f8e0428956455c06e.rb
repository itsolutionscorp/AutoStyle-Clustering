#!/usr/bin/env ruby

# Exercism 19
# Series Test

# Write a program that will take a string of digits and give you all
# the possible consecutive number series of length `n` in that string.

class Series
  def initialize(string)
    @digits = string
  end

  def slices(num)
    raise ArgumentError if num > @digits.length

    slices = []
    (@digits.length - num + 1).times { |x| slices << @digits.slice(0+x, num).split('').map{ |x| x.to_i } }
    slices
  end
end
