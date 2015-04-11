class Squares 
  def initialize(number)
        @number = number
  end

  def sum_of_squares
    (1..@number).inject{ |sum, value| sum + value**2  } 
  end

  def square_of_sums
    (1..@number).inject{ |sum, value| sum + value} **2
  end
  
  def difference 
    square_of_sums - sum_of_squares
  end

end
