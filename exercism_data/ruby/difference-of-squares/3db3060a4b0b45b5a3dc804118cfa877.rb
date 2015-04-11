class Squares

  def initialize(final_number)
    @numbers = (1..final_number)
  end

  def square_of_sums
    @numbers.reduce(:+)**2
  end

  def sum_of_squares
    @numbers.reduce(0){ |sum, number| sum + number ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
