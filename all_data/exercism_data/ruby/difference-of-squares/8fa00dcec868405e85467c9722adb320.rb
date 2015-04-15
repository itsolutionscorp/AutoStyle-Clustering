class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    (0..@n).inject(&:+) ** 2
  end

  def sum_of_squares
    (0..@n).map { |x| x ** 2}.inject(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
