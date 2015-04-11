# Manipulate squares
class Squares

  def initialize(number)
    @range = (1..number)
  end

  def square_of_sums
    @range.inject { |sums, n| (sums + n) } ** 2
  end

  def sum_of_squares
    @range.inject { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
