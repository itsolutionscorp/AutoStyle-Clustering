class Squares
  def initialize(x)
    @x = x
  end

  def sum_of_squares
    range = (1..@x).to_a
    range.inject(0){|sum, num| sum + num**2}
  end

  def square_of_sums
    other_range = (1..@x).to_a
    other_range.inject(0){|sum, num| sum + num} ** 2
  end

  def difference
    square_of_sums - sum_of_squares 
  end

end
