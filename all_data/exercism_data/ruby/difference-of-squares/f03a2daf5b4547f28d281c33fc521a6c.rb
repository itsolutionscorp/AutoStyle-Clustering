class Squares
  def initialize(limit)
    @range = (1..limit)
  end

  def square_of_sums
    @range.reduce(:+) ** 2
  end

  def sum_of_squares
    @range.reduce(0) { |sum, x| sum + (x ** 2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
