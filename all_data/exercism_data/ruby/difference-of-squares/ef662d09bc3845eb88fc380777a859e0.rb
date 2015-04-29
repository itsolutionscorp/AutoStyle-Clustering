class Squares

  def initialize(limit)
    @limit = limit
  end

  def range
    (1..@limit)
  end

  def square_of_sums
    range.reduce(:+)**2
  end

  def sum_of_squares
    range.reduce do |sum, num|
      sum += num **2
    end
  end

  def difference
    square_of_sums - sum_of_squares 
  end

end
