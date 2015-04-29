class Squares
  attr_reader :max
  
  def initialize(max)
    @max = max 
  end

  def square_of_sums
    (1..max).map { |number| number }.reduce(:+) ** 2
  end

  def sum_of_squares
    (1..max).map { |number| number ** 2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
