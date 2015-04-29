class Squares 

  attr_reader :n

  def initialize n
    @n = n
  end

  def square_of_sums 
    sum = operate_with { |sum, number| sum + number }
    sum_squared = sum**2
  end

  def sum_of_squares 
    operate_with { |sum, number| sum + number**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

private

  def operate_with &block
    (1..n).inject &block
  end

end
