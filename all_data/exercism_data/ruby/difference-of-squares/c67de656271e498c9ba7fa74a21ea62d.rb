class Squares
  def initialize(num)
    @first_numbers = num.times.map { |i| i+1 }
  end

  def square_of_sums
    @first_numbers.reduce(:+)**2
  end

  def sum_of_squares
    @first_numbers.map { |i| i**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
