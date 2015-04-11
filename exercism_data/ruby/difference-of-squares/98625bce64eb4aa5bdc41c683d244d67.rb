require 'pry'

class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    sum = 1.upto(@n).to_a.inject(0) {|sum, x| sum + x}
    sum**2
  end

  def sum_of_squares
    1.upto(@n).to_a.inject(0) {|sum, x| sum + x**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
