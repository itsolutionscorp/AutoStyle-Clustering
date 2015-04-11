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
    @number.times.inject(0) { |sum, i| sum + i**2 }
  end
end
