class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    square(sum)
  end

  def sum_of_squares
    sum(square)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def square(values = (1..@num))
    if values.is_a? Range
      values.map { |num| num ** 2 }
    else
      values ** 2
    end
  end

  def sum(values = (1..@num))
    values.inject(0, &:+)
  end

end
