class Squares

  def initialize(limit)
    @range = (1..limit)
  end

  def sum_of_squares
    @range.map{|i| i ** 2 }.reduce(:+) 
  end

  def square_of_sums
    @range.reduce(:+) ** 2 
  end

  def difference
    square_of_sums - sum_of_squares 
  end

end
