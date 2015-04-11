class Squares
  def initialize(max)
    @max = max
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

  def square_of_sums
    1.upto(@max).reduce(0, :+) ** 2
  end

  def sum_of_squares
    1.upto(@max).map { |n| n ** 2 }.reduce(0, :+)
  end
end
