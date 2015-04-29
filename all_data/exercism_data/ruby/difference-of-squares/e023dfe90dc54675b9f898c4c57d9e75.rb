class Squares
  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
    sum = (1..@limit).reduce (:+)
    
    sum * sum
  end

  def sum_of_squares
    sum = (1..@limit).inject {|sum, i| sum += i*i}
  end

  def difference
    square_of_sums - sum_of_squares 
  end
end
