class Squares
  def initialize(number)
    @number = number + 1
  end

  def square_of_sums
    @number.times.reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    @number.times.map { |i| i**2 }.reduce(:+)
  end
end
