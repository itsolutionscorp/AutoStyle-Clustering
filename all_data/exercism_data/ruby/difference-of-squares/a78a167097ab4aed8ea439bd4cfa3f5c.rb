class Squares
  attr_reader :square_of_sums, :sum_of_squares

  def initialize(number)
    @number = number
    @square_of_sums = (1..number).inject(&:+)**2
    @sum_of_squares = (1..number).inject { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
