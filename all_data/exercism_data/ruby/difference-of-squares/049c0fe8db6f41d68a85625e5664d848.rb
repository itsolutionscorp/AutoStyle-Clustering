class Squares
  @number

  def initialize(number)
    @number=number
  end

  def square_of_sums
    result = (1..@number).inject { |acc, x| acc + x }
    result**2
  end

  def sum_of_squares
    (1..@number).inject { |acc, x| acc + x**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
 
end
