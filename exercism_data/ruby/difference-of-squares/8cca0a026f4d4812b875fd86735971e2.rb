class Squares
  def initialize(number)
    @numbers = 1..number
  end

  def square_of_sums
    @numbers.inject(:+) ** 2
  end

  def sum_of_squares
    @numbers.inject { |sum, number| sum + number ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
