class Squares
  def initialize(num)
    @num = num
  end

  public
  def sum_of_squares
    sum = 0
    @num.times{|i| sum += (i+1)**2}
    sum
  end

  def square_of_sums
    sum = 0
    @num.times{|i| sum+=(i+1)}
    sum**2
  end

  def difference
    (self.sum_of_squares-self.square_of_sums).abs
  end

end
