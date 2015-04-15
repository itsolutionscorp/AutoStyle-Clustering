class Squares
  def initialize(number)
    @number = number
  end

  def numbers
    (1..@number)
  end

  def square_of_sums
    numbers.inject(:+)**2
  end

  def sum_of_squares
    numbers.inject(0){|sum,x| sum + x**2}
  end

  def difference
     square_of_sums - sum_of_squares
  end

end
