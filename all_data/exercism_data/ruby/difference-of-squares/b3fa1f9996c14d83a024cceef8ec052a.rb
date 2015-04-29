class Squares
  attr_reader :n

  def initialize(n)
    @n = n.to_i
    raise 'Must be an integer > 0.' if @n < 1
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    (@n * (@n + 1) / 2) ** 2
  end

  def sum_of_squares
    (2 * @n ** 3 + 3 * @n ** 2 + @n) / 6
  end
end
