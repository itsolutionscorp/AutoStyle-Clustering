class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    for_range.reduce(:+) ** 2
  end

  def sum_of_squares
    for_range.map { |x| x ** 2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def for_range
    (1..@number)
  end
end
