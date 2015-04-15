#!/usr/bin/env ruby

class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    # (n (n + 1))**2 / 2**2
    ((@num * (@num + 1))**2) / 4
  end

  def sum_of_squares
    (1..@num).reduce { |a, e| a + e**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
