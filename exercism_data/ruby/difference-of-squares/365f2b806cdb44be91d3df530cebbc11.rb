class Squares
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    (0..@number).inject { |sum, count| sum+=count**2 }
  end

  def square_of_sums
    (0..@number).reduce(:+)**2
  end

  def difference
    Squares.new(@number).square_of_sums - Squares.new(@number).sum_of_squares
  end
end