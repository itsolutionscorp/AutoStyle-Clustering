class Squares
  attr_reader :number
  def initialize i
    @number = i
  end

  def square_of_sums
    @square_of_sums ||= (1..@number).reduce(:+) ** 2
  end

  def sum_of_squares
    @sum_of_squares ||= (1..@number).reduce { |x, y| x + (y ** 2) }
  end

  def difference
    @difference ||= square_of_sums - sum_of_squares
  end
end
