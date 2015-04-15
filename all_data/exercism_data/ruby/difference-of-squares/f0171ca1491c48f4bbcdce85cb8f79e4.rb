class Squares
  def initialize(num)
    @range = (1..num) #generates the numbers to num (1,2,3,4)
  end

  def square_of_sums
    @range.inject(:+)**2 # :+ creates the block to add each to the sum
  end

  def sum_of_squares
    @range.inject {|result, number| number**2 + result }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end 
