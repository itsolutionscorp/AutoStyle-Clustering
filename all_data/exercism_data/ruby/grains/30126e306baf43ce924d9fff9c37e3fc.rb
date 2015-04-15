#!/usr/bin/env ruby -w
# encoding: UTF-8

class Grains

  MAX_ID = 64

  def square(id)
    fail unless valid_square?(id)
    2**(id - 1)
  end

  def total
    2 * square(MAX_ID) - 1
  end

  private

  def valid_square?(id)
    (1..MAX_ID).include?(id)
  end

  def fail
    raise(ArgumentError, "Given ID not in (1..#{MAX_ID})")
  end

end
