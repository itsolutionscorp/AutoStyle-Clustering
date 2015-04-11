class Squares
  def initialize(number)
    @number = number
  end
  
  def square_of_sums
    sum = (1..@number).to_a.inject(0) { |sum, num| sum + num }
    sum**2
  end
  
  def sum_of_squares
    (1..@number).to_a.inject(0) { |sum, num| sum + num**2 }
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
