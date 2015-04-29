class Squares
  def initialize(number)
    @range = 1..number
  end

  def square_of_sums
    sum(@range)**2
  end

  def sum_of_squares
    sum(@range.map { |n| n**2 })
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum(enumerable)
    enumerable.reduce(:+)
  end
end
