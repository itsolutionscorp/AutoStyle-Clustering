class Squares
  def initialize(limit)
    @range = (1..limit)
  end

  def square_of_sums
    sum = @range.reduce (:+)
    
    sum * sum
  end

  def sum_of_squares
    sum = @range.inject {|sum, i| sum += i*i}
  end

  def difference
    square_of_sums - sum_of_squares 
  end
end
