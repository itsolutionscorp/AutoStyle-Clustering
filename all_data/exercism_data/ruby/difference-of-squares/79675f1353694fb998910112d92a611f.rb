class Squares
  attr_reader :range

  def initialize(num)
    @range = (0..num)

  end

  def square_of_sums
    (range.inject { |sum, n| sum + n })**2
  end

  def sum_of_squares
    range.inject { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
