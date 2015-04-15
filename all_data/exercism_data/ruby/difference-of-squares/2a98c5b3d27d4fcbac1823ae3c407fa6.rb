class Squares
  def initialize(arg)
    @max = arg
  end

  def square_of_sums
    (1..@max).inject(:+) ** 2
  end

  def sum_of_squares
    (1..@max).map { |num| num * num }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
