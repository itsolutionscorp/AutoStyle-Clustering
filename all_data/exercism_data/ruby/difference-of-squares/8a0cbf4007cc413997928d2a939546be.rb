require_relative 'squares'
require 'minitest/autorun'

class Squares

  attr_reader :n

  def initialize(n)
    @n = n
  end

  def square_of_sums
    ((n**4) + 2*(n**3) + (n**2)) / 4
  end

  def sum_of_squares
    (1..n).map { |e| e**2 }.inject(0) { |r,e| r += e }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
