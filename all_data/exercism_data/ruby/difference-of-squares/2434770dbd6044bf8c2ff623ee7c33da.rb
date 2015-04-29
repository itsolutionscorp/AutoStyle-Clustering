class Squares
  attr_reader :number
  
  def initialize(number)
    @number = number
  end
  
  def square_of_sums
    square_of_sums = 0
    (1..number).each do |num|
      square_of_sums += num
    end
    square_of_sums**2
  end
  
  def sum_of_squares
    sum_of_squares = 0
    (1..number).each do |num|
      sum_of_squares += (num**2)
    end
    sum_of_squares
  end
  
  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
