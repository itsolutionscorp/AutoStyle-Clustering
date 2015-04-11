class Squares

  def initialize(number)
    @range = 1..number
  end

  def square_of_sums
    @range.inject(:+)**2
  end

  def sum_of_squares
    @range.to_a.map { |num| num**2}.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
