class Squares
  def initialize(positive_integer)
    @numbers = 1..positive_integer
  end

  def sum_of_squares
    @numbers.map {|number| square(number)}.reduce(:+)
  end

  def square_of_sums
    square(@numbers.reduce(:+))
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square(number)
    number ** 2
  end
end
