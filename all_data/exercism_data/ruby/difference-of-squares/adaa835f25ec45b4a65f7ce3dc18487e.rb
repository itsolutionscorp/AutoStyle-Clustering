class Squares
  def initialize(number)
    @number = number
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    numbers.reduce(:+).abs2
  end

  def sum_of_squares
    numbers.map(&:abs2).reduce(:+)
  end

  def numbers
    1..@number
  end
end
