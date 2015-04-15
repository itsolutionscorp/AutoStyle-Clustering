class Squares
  attr_reader :count

  def initialize(count)
    @count = count
  end

  def square_of_sums
    triangle_number ** 2
  end

  def sum_of_squares
    # Faulhaber's formula for square pyramidal numbers
    (@count * (@count + 1) * ((2*@count) + 1)) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def triangle_number
    (@count * (@count+1)) / 2
  end

end
