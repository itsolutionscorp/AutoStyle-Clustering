class Squares
  def initialize(number)
    @range = (1..number)
  end

  def square_of_sums
    @range.reduce(:+) ** 2
  end

  def sum_of_squares
    @range.reduce { |sum, n| sum + n ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
