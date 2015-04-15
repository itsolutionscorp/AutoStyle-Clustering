class Grains
  ALL_SQUARES = (1..64)

  def square(number)
    2**(number - 1)
  end

  def total
    sum_of_squares
  end

  private

  def list_of_squares
    ALL_SQUARES.map{|number| square(number) }
  end

  def sum_of_squares
    list_of_squares.reduce(:+)
  end
end
