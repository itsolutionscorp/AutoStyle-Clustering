class Squares
  def initialize num
    @nums = (1 .. num)
  end
  
  def square_of_sums
    @nums.reduce(:+) **2
  end
  
  def sum_of_squares
    @nums.map{|x| x **2}.reduce(:+)
  end
  
  def difference 
    square_of_sums - sum_of_squares
  end
end
