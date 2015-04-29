class Squares

  def initialize( max_number )
    @max_number = max_number
  end

  def square_of_sums
    sum = range.inject(0, &:+)
    sum**2
  end

  def sum_of_squares
    squares = range.map { |n| n**2 }
    squares.inject(0, &:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private
    def range
      (1..@max_number)
    end

end
