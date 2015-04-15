# version 1

class Squares
  
  def initialize(number)
    @number = number
  end
  
  def difference
    return square_of_sums - sum_of_squares
  end

  def square_of_sums
    (1..@number).inject { |sum, i| sum += i }**2
  end
  
  def sum_of_squares
    (1..@number).inject { |sum, i| sum += i**2 }
  end
  
end
