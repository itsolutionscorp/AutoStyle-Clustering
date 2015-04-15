class Squares
  def initialize(num)
    @nums = (1..num)
  end

  def square_of_sums
    sum = @nums.inject(0, &:+)
    sum * sum
  end

  def sum_of_squares
    result = @nums.inject(0) do |sum, num|
      sum + (num * num)
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
