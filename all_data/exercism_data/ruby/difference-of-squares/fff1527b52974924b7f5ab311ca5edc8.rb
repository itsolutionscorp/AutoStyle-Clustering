class Squares

  attr_reader :range

  def initialize(n)
    @range = (1..n).to_a
  end

  def square(n)
    n**2
  end

  def sum_of_squares
    range.map { |n| square(n) }.reduce(:+)
  end

  def square_of_sums
    square(range.reduce(:+))
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
