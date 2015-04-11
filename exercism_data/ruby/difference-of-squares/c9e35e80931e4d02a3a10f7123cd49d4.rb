class Squares

  def initialize(number)
    @range = 1..number
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    (@range.reduce(0){ |sum, value| sum + value**2})
  end

  def square_of_sums
    (@range.reduce(0) { |sum, value| sum + value}) ** 2
  end
end
