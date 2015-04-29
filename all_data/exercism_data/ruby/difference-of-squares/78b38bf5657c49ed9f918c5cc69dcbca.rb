class Squares
  def initialize n 
    @n = n
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    square sum naturals  
  end

  def sum_of_squares
    sum squares 
  end

private

  def naturals
    1..@n
  end

  def sum sequence 
    sequence.reduce 0, :+ 
  end

  def square n 
    n ** 2
  end

  def squares
    naturals.map { |n| square n }
  end
end
