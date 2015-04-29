#!/usr/bin/env ruby -w
# encoding: UTF-8

class Grains

  MAX_SQUARE_NUMBER = 64

  def square(n)
    check_valid_square(n)
    2**(n-1)
  end

  def total
    2 * square(MAX_SQUARE_NUMBER) - 1
  end

  def check_valid_square(n)
    unless (1..MAX_SQUARE_NUMBER).include?(n)
      raise(ArgumentError, "n is not in (1..#{MAX_SQUARE_NUMBER})")
    end
  end

end
