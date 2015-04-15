class Squares

  def initialize( number )
    @range = (1..number).to_a
  end

  def sum_of_squares
    sum_of_squares = 0
    @range.each do |x|
      sum_of_squares += x**2
    end
    return sum_of_squares
  end

  def square_of_sums
    square_of_sums = 0
    @range.each do |x|
      square_of_sums += x
    end
    return square_of_sums**2
  end

  def difference
    return self.square_of_sums - self.sum_of_squares
  end

end
