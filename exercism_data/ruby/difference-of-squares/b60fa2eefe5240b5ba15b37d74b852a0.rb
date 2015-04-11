# version 1

class Squares
  
  def initialize(number)
    @number = number
  end
  
  def difference
    return square_of_sums - sum_of_squares
  end

  def square_of_sums
    sum = 0
    1.upto(@number) { |i| sum += i }
    return sum**2
  end
  
  def sum_of_squares
    sum = 0
    1.upto(@number) { |i| sum += i**2 }
    return sum
  end
  
end
