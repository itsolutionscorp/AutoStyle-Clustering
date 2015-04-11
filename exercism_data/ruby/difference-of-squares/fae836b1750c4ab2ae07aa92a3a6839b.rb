class Squares
  def initialize(num)
    @range = (1..num).to_a #generates an array [1,2,3,4 .. up to the value of num]
  end

  def square_of_sums
    (@range.inject {|result, number| number + result})**2
  end

  def sum_of_squares
    @range.inject {|result, number| number**2 + result }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end 
