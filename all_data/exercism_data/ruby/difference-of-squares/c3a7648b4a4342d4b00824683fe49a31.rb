class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    range.reduce(:+).abs2
  end

  def sum_of_squares
    range.map(&:abs2).reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def range
    (1..@num)
  end
end
