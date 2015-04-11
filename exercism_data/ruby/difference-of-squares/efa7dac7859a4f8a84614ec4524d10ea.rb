class Squares

  def initialize(num)
    # take num and creates an instance variable so we can use it in other methods
    @num = num
  end
  
  def square_of_sums
    # basically a for loop.  Takes every number from 1 to @num, then creates a 
    # variable(sum) and puts the number in n's place. After the loop is completed,
    # take the square of sum and return that value
    (1..@num).inject { |sum, n| sum += n } **2 
  end
  
  def sum_of_squares
    # same as above except we square @num first then add it to sum
    (1..@num).inject { |sum, n| sum += n**2 }
  end
  
  def difference
    # returns the difference of the two methods
    square_of_sums - sum_of_squares
  end

end
