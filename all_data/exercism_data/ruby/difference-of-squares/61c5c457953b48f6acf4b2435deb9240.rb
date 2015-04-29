class Squares
  def initialize(number)
    @range = Array(1..number)
  end

  def square_of_sums
    sum = 0
    @range.each do |x|
      sum+=x
    end
    sum**2
  end

  def sum_of_squares
    sum = 0
    @range.each do |x|
      sum += x**2
    end
    sum
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
