class Squares
  attr_reader :value

  def initialize value
    @value = value
  end

  def sum_of_squares
    (1..value).inject(0) { |sum, i| sum + i**2 }
  end

  def square_of_sums
    (1..value).inject(0) { |sum, i| sum + i }**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
