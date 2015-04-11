class Squares
  def initialize(num)
    @num = num
  end
  def sum_of_squares
    sum = 0
    (0..@num).each {|x| sum += x**2 }
    sum
  end

  def square_of_sums
    other_sum = 0
    (0..@num).each {|x| other_sum += x}
     other_sum **2
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
