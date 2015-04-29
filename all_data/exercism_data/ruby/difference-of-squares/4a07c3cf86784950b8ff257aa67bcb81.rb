class Squares
  def initialize(arg)
    @arg = arg
  end

  def square_of_sums
    (1..@arg).reduce(:+) ** 2
  end

  def sum_of_squares
    (1..@arg).map {|n| n ** 2}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
