class Squares
  
  def initialize(num)
    @number = num
  end
  
  def square_of_sums
    sum=0
    x=0
    while x<=@number do
      sum += x
      x=x+1
    end 
    sum**2   
  end
  
  def sum_of_squares
    sum = 0
    y =0
    while y <= @number do
      sum += y*y
      y = y + 1
    end
    sum
  end
  
  def difference
    square_of_sums-sum_of_squares
  end
end
