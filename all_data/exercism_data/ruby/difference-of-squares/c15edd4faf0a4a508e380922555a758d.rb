class Squares
  def initialize(num)
    @number = num
  end

  def square_of_sums
    (1..@number).reduce(:+)**2
  end

  def sum_of_squares
    (1..@number).reduce { |sum, n| sum += n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
