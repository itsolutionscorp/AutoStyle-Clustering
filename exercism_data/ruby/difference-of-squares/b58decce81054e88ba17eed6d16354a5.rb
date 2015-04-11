class Squares
  def initialize(val)
    @val = val
  end

  def square_of_sums
    (1..@val).inject(:+) ** 2
  end

  def sum_of_squares
    (1..@val).map { |x| x**2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
