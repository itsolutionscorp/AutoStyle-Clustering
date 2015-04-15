class Squares
attr_accessor :num
  
  def initialize(number)
    @num = number
  end

  def square_of_sums
    ((1..@num).inject { |tot, x| x + tot })**2
  end

  def sum_of_squares
    (1..@num).inject { |sum, x| (x**2) + sum }
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
