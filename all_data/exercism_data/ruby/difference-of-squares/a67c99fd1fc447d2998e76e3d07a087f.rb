class Squares

  def initialize(num)
    @numbers = num
  end

  def square_of_sums
    (1..numbers).inject { |sum, n| sum + n } ** 2
  end

  def sum_of_squares
    (1..numbers).inject { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private
  attr_reader :numbers

end
