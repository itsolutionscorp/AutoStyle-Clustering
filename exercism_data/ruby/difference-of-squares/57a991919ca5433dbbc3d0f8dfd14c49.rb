#!/usr/bin/env ruby

# Exercism 5
# Difference of Squares
# Find the difference between the sum of the squares and the 
# square of the sums of the first N natural numbers.

class Squares

  def initialize(num)
    @range = 1..num
  end

  def square_of_sums
    sum = 0
    @range.collect { |x| sum += x }
    sum**2
  end

  def sum_of_squares
    sum = 0
    @range.collect { |x| sum += x**2 }
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
