class Squares

  def initialize(number)
    @number = number
  end

  def square_of_sums 
    (1..@number).reduce(:+) ** 2
  end

  def sum_of_squares
    (1..@number).reduce { |memo, num| memo += (num**2) }
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
