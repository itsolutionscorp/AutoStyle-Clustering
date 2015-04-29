require 'pry'

class Squares

  def initialize(value)
    @value = value
  end

  def square_of_sums
    (1..@value).reduce(0,:+)**2
  end

  def sum_of_squares
    (1..@value).inject(0){ |square, n| square + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
