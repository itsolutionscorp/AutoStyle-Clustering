class Squares
  attr_accessor :number

  def initialize(number)
    self.number = number
  end

  def square_of_sums
    number_range.reduce(:+) ** 2
  end

  def sum_of_squares
    number_range.map { |n| n*n }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def number_range
    0..number
  end

end
