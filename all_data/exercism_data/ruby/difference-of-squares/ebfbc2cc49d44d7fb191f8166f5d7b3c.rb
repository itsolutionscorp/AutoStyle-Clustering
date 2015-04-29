class Squares
  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
    sum = 0
    1.upto(@limit) {|i| sum += i}
    
    sum * sum
  end

  def sum_of_squares
    sum = 0
    1.upto(@limit) {|i|sum += i * i}

    sum
  end

  def difference
    square_of_sums - sum_of_squares 
  end
end
