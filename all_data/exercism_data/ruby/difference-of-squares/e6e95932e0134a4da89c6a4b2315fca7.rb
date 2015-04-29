class Squares
  def initialize(count)
    @numbers = 1..count
  end

  def square_of_sums
    @numbers.inject(&:+)**2
  end

  def sum_of_squares
    @numbers.map { |n| n**2 }.inject(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
