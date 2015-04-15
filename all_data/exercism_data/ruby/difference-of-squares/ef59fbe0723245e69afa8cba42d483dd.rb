class Squares

  attr_reader :n

  def initialize n
    @n = n
  end

  def square_of_sums
    (1..n).inject(&:+) ** 2
  end

  def sum_of_squares
    (1..n).map { |e| e**2 }.inject(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
