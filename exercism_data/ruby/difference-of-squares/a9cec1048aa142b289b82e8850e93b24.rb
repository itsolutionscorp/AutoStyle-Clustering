class Squares

  # Initialize with integer n
  def initialize n
    @number = n
  end

  # Returns the square of the sum of the integers leading & including @number
  def square_of_sums
    sum = 0
    1.upto @number do |i|
      sum += i
    end
    sum**2
  end

  # Returns the sum of the squares of the integers leading & including @number
  def sum_of_squares
    sum = 0
    1.upto @number do |i|
      sum += i**2
    end
    sum
  end

  # Returns the difference between the square of sums minus the sum of squares
  def difference
    square_of_sums - sum_of_squares
  end

end
