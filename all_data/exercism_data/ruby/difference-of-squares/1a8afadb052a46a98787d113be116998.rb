class Squares
  attr_reader :up_to

  def initialize(up_to)
    @up_to = up_to
  end

  def sum_of_squares
    (1..up_to).inject{ |sum, number| sum + (number * number)}
  end

  def square_of_sums
    ((1..up_to).inject(:+)) * ((1..up_to).inject(:+))
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
