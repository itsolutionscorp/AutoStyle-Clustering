class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    numbers.reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    numbers.reduce(0) { |sum, i| sum + i**2 }
  end

  private

  def numbers
    (1..@number)
  end
end
