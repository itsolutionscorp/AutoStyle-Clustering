# class to calculate difference between square of sums and sum of sqaures
class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    (1..@n).reduce(&:+)**2
  end

  def sum_of_squares
    (1..@n).map { |i| i**2 }.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
