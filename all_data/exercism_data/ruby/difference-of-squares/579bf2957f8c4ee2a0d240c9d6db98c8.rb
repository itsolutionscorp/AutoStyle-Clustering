class Squares

  def initialize(num)
    @series = 1..num
  end

  def square_of_sums
    sum(@series)**2
  end

  def sum_of_squares
    sum(@series.map{ |num| num**2 })
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sum(enumerable)
    enumerable.reduce(&:+)
  end
end
