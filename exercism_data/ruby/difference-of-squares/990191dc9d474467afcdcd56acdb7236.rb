require 'pry'

class Squares

  attr_reader :nums

  def initialize(n)
    @nums = (1..n).to_a
  end

  def square_of_sums
    nums.inject(:+) ** 2
  end

  def sum_of_squares
    nums.collect{|n| n**2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
