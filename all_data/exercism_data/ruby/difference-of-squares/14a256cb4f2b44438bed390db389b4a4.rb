class Squares
  
  def initialize(natural_limit)
    @range = 1..natural_limit
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
  
  def sum_of_squares
    # reduce range to the sum of it's squares
    @range.reduce(0) do |memo, number|
      memo + number**2
    end
  end
  
  def square_of_sums
    #reduce the range to the sum of it's numbers, then square the result
    @range.reduce(:+)**2
  end
end
