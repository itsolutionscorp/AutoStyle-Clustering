class Squares
  def initialize(size)
    @number_range = (1..size).to_a
  end

  def square_of_sums
    @number_range.reduce(&:+) ** 2
  end

  def sum_of_squares
    @number_range.map {|n| n ** 2}.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
