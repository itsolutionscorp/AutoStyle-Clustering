class Squares
  def initialize(number)
    @numbers = 1..number
  end

  def square_of_sums
    total = @numbers.reduce(:+)
    total ** 2
  end

  def sum_of_squares
    @numbers.reduce { |total, x| total + (x * x) }
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
