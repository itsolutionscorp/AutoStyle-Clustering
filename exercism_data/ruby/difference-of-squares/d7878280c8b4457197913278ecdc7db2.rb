class Squares
  def initialize(top)
    @top = top
  end

  def square_of_sums
    sum = (1..@top).reduce(&:+)
    sum**2
  end

  def sum_of_squares
    (1..@top).map { |n| n ** 2 }.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
