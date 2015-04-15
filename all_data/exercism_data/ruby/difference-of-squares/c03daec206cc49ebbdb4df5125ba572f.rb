class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).reduce(0, :+) ** 2
  end

  def sum_of_squares
    (1..@num).map { |num| num ** 2 }.reduce(0, :+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
