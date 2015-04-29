class Squares
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    sum = 0
    (0..@number).each do |count|
      sum+=count**2
    end
    sum
  end

  def square_of_sums
    squared_sum = 0
    (0..@number).each do |count|
      squared_sum+=count
    end
    squared_sum**2
  end

  def difference
    Squares.new(@number).square_of_sums - Squares.new(@number).sum_of_squares
  end
end
