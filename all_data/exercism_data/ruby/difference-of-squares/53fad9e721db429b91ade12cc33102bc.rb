class Squares
  attr_reader :nums
  def initialize(numbers)
    @nums = numbers
  end

  def sum_of_squares
    (1..nums).inject(0) { |a, e| a + e**2 }
  end

  def square_of_sums
    ((1..nums).inject(0) { |a, e| a + e })**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
