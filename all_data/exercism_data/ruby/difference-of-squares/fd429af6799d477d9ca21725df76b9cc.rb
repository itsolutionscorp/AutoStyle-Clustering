class Squares
  def initialize(range_max)
    @range_max = range_max
  end

  def square_of_sums
    (1..@range_max).reduce(:+)**2
  end

  def sum_of_squares
    (1..@range_max).map { |n| n**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end


end
