#!/usr/bin/env ruby -w
# encoding: UTF-8

class Grains

  MAX_ID = 64

  def square(n)
    raise(ArgumentError, "n not in (1..#{MAX_ID})") unless valid_square?(n)

    2**(n-1)
  end

  def total
    2 * square(MAX_ID) - 1
  end

  def valid_square?(n)
    (1..MAX_ID).include?(n)
  end

end
