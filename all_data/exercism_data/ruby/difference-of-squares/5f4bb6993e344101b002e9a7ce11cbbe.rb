class Squares
  def initialize(range)
    @range = (1..range)
  end

  def square_of_sums
    @range.reduce(:+)**2
  end

  def sum_of_squares
    @range.reduce(0) { |sum, num| sum + num**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
