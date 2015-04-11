class Squares
  def initialize(limit)
    @range = 1..limit
  end

  def square_of_sums
    @range.reduce(&:+) ** 2
  end

  def sum_of_squares
    @range.map { |n| n ** 2 }.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
