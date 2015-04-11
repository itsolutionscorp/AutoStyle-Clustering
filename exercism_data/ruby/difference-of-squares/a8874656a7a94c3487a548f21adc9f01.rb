class Squares

  def initialize(numIn)
	@numbers = (1..numIn)
  end

  def square_of_sums
	@numbers.inject(:+) ** 2
  end

  def sum_of_squares
	(@numbers.map {|num| num**2}).inject(:+)
  end

  def difference
	(square_of_sums - sum_of_squares)
  end
end
