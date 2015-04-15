class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    (0..@n).inject(&:+).abs2
  end

  def sum_of_squares
    (0..@n).map(&:abs2).inject(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
