class Squares
  attr_reader :n

  def initialize(number)
    @n = number
  end

  def square_of_sums
    sum_of_numbers**2
  end

  def sum_of_squares
    sum_of_numbers * (2 * n + 1) / 3
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sum_of_numbers
    n * (n + 1) / 2
  end
end
