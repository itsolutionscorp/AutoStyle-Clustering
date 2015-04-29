class Squares

  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum = (1..@number).inject{ |sum, x| sum + x } ** 2
  end

  def sum_of_squares
    (1..@number).inject{ |sum, x| sum + x**2 }
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
