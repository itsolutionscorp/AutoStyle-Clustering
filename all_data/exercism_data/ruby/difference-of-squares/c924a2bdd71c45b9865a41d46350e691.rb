class Squares
  def initialize num
    @num = num
  end

  def square_of_sums
    ((1..@num).each.inject { |acc, e| acc + e})**2
  end

  def sum_of_squares
    (1..@num).each.inject { |acc, e| acc + e**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
