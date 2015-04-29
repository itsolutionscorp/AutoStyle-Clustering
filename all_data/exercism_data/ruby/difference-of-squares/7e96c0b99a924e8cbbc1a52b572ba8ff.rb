class Squares
  def initialize n
    @num = n
  end

  def sum_of_squares
    sum (1..@num).map { |n| n ** 2 }
  end

  def square_of_sums
    sum((1..@num)) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private
  def sum iter
    iter.reduce(0, :+)
  end
end
