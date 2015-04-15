class Squares
  def initialize(total)
    @total = total
  end

  def sum_of_squares
    value_range.inject(0) { |result, n| result + n ** 2 }
  end

  def square_of_sums
    (value_range.inject(0, :+)) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def value_range
    (1..@total)
  end
end
