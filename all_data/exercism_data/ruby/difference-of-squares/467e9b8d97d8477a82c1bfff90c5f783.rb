class Squares
  attr_reader :limit
  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
    (1..limit).reduce(:+)**2
  end

  def sum_of_squares
    (1..limit).map { |i| i**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
