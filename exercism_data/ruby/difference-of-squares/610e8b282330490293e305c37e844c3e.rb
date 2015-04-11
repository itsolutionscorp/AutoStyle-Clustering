class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    (0..@number).reduce(0,&:+)**2
  end

  def sum_of_squares
    (0..@number).reduce(0){|sum, num| sum + num**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
