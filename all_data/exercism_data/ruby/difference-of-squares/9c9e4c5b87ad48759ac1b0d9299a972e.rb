class Squares
  attr_reader :upper_bound

  def initialize(upper_bound)
    @upper_bound = upper_bound
  end

  def sum_of_squares
    (1..upper_bound).inject{ |sum, number| sum + (number * number)}
  end

  def square_of_sums
    ((1..upper_bound).inject(:+)) * ((1..upper_bound).inject(:+))
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
