#!/usr/bin/env ruby

# Exercism 10
# Grains
# Count number of grains that would remain after doubling 64 times

class Grains

  def square(num)
    2 ** (num - 1)
  end

  def total
    (1..64).reduce { |sum, grains| sum + square(grains) }
  end

end
