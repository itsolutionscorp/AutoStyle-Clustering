class Squares
  def initialize(positive_integer)
    @natural_numbers = 1..positive_integer
  end

  def sum_of_squares
    @natural_numbers.map{|number| square(number)}.reduce(:+)
  end

  def square_of_sums
    square(@natural_numbers.reduce(:+))
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def square(number)
    number ** 2
  end
end
