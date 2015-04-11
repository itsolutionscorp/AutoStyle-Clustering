class Squares
  def initialize(number)
    @enum = (1..number)
  end

  def square_of_sums
    sum = 0
    @enum.map { |i| sum += i }
    sum ** 2
  end

  def sum_of_squares
    sum = 0
    @enum.map { |i| sum += i**2 }
    sum
  end

  def difference
    square_of_sums() - sum_of_squares()
  end
end
