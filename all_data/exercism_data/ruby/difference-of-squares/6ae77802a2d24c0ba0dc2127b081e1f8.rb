class Squares
  attr_reader :range

  def initialize(n)
    @range = *(1..n)
  end

  def square_of_sums
    range.reduce(:+)**2
  end

  def sum_of_squares
    range.reduce {|sum, num| sum += num**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
