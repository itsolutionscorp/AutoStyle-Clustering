class Squares

  attr_reader :n
  def initialize(n)
    @n = n
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

  def square_of_sums
    square(sum(1..n))
  end

  def sum_of_squares
    sum((1..n).map { |num| square(num) })
  end

  private
  def square(n)
    n ** 2
  end

  def sum(numbers)
    numbers.inject(&:+)
  end
end
