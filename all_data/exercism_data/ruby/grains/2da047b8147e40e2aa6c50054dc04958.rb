#!/usr/bin/env ruby

# Exercism 10
# Grains
# Count number of grains that would remain after doubling 64 times

class Grains

    @@squares = []

  def square(num)
    @@squares = num.times.collect { |x| 2**x }
    @@squares.last
  end

  def total
    total = 0
    @@squares.each { |x| total += x }
    total
  end

end
