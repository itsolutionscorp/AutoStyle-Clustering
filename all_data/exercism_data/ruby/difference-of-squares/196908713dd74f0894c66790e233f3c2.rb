class Squares
  def initialize x
    @x = x
  end
  def square_of_sums
    sum = 0
    @x.times{|i|sum+=i+1}
    sum**2
  end
  def sum_of_squares
    sum = 0
    @x.times{|i|sum+= (i+1)**2}
    sum
  end
  def difference
    square_of_sums-sum_of_squares
  end
end
