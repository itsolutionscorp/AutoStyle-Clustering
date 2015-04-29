class Squares
  def initialize (n)
    @num=n
  end
  
  def square_of_sums
    sum=((@num+1)*@num/2)
    sum**2
  end

  def sum_of_squares
    sum=0
    (1..@num).each do |x|
      sum+=x**2
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
