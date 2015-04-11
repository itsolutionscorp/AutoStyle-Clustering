class Squares
  def initialize(limit)
    @values = 1..limit
  end

  def square_of_sums
    @values.reduce(:+) ** 2
  end

  def sum_of_squares
    @values.inject(0) { |sum, value| sum + (value ** 2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
