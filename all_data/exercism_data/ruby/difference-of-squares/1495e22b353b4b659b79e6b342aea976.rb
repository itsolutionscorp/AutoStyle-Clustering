class Squares

  def initialize(number)
    @number = number
  end

  def square_of_sums
    ((1..@number).reduce(:+))**2
  end

  def sum_of_squares
    (1..@number).inject { |sum, num| sum + num**2 }
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end
end
