class Squares
  attr_reader :number 
  
  def initialize(input_number) 
    @number = input_number
  end

  def square_of_sums
    ((number ** 2 + number) / 2) ** 2
  end

  def sum_of_squares
    (number * (number + 1) * (2 * number + 1)) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
