class Squares
  def initialize(number)
    @range = (1..number)
  end

  def square_of_sums
    @square_of_sums ||= @range.inject(:+)**2
  end

  def sum_of_squares
    @sum_of_squares ||= @range.inject(0) { |sum, number| sum + number**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
