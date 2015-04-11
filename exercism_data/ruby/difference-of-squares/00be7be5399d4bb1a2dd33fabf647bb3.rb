class Squares

  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
    sum = (1..@limit).inject {|sum, i| sum += i}
    sum**2
  end

  def sum_of_squares
    (1..@limit).inject {|sum, i| sum += i**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
